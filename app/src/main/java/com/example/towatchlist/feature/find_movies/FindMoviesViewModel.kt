package com.example.towatchlist.feature.find_movies

import androidx.lifecycle.MutableLiveData
import com.example.towatchlist.architecture.base.BaseViewModel
import com.example.towatchlist.architecture.base.ResultError
import com.example.towatchlist.architecture.base.ResultSuccess
import com.example.towatchlist.model.remote.entity.MovieListResultObject
import com.example.towatchlist.utils.notifyObserver
import kotlinx.coroutines.launch
import java.lang.Exception

class FindMoviesViewModel(
    private val findMoviesInteractor: IFindMoviesInteractor
) : BaseViewModel() {
    private var searchQuery: String? = null
    private var searchPages = 1
    private var currentPage = 1
    private val searchResults = ArrayList<MovieListResultObject.SearchMovieResponseResult>()

    val searchResultsLiveData = MutableLiveData<ArrayList<MovieListResultObject.SearchMovieResponseResult>>()
    val searchResultsErrorLiveData = MutableLiveData<Exception?>()

    fun getWeeklyTrendingMovies() {
        launch {
            val result = findMoviesInteractor.getWeeklyTrendingMovies()
            when (result) {
                is ResultSuccess -> {
                    searchResultsLiveData.value = ArrayList(result.data.results)
                    searchResultsErrorLiveData.value = null
                }
                is ResultError -> searchResultsErrorLiveData.value = result.exception
            }
        }
    }

    fun searchMovies(query: String) {
        launch {
            if (!validateSearchQuery(query)) {
                resetSearchParameters()//temp
                searchResultsErrorLiveData.value = Exception()
                return@launch
            }

            resetSearchParameters(query)

            val result = findMoviesInteractor.searchMovies(query)
            when (result) {
                is ResultSuccess -> {
                    searchPages = result.data.totalPages
                    searchResultsLiveData.value?.clear()
                    searchResultsLiveData.value?.addAll(result.data.results)
                    searchResultsLiveData.notifyObserver()

                    searchResultsErrorLiveData.value = null
                }
                is ResultError -> searchResultsErrorLiveData.value = result.exception
            }
        }
    }

    fun appendSearchResult() {
        launch {
            if (currentPage >= searchPages) return@launch

            searchQuery?.also { searchQuery ->
                currentPage++

                val result = findMoviesInteractor.searchMovies(searchQuery, currentPage)

                when (result) {
                    is ResultSuccess -> {
                        searchResultsLiveData.value?.addAll(result.data.results)
                        searchResultsLiveData.notifyObserver()

                        searchResultsErrorLiveData.value = null
                    }
                    is ResultError -> searchResultsErrorLiveData.value = result.exception
                }
            }
        }
    }

    fun restoreSearchResults() {
        launch {
            searchQuery?.let {
                searchResultsLiveData.notifyObserver()
            } ?: getWeeklyTrendingMovies()
        }
    }

    private fun resetSearchParameters(query: String? = null) {
        searchQuery = query
        searchPages = 1
        currentPage = 1
        searchResults.clear()
    }

    private fun validateSearchQuery(query: String): Boolean {
        return query.isNotBlank()
    }
}