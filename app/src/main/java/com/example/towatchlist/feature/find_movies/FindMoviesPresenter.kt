package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.ResultError
import com.example.towatchlist.architecture.base.ResultSuccess
import com.example.towatchlist.model.remote.entity.MovieListResultObject
import kotlinx.coroutines.launch

class FindMoviesPresenter(private val findMoviesInteractor: FindMoviesContract.Interactor) :
    FindMoviesContract.Presenter() {
    private var searchQuery: String? = null
    private var searchPages = 1
    private var currentPage = 1
    private val searchResults = ArrayList<MovieListResultObject.SearchMovieResponseResult>()

    override fun getWeeklyTrendingMovies() {
        launch {
            val result = findMoviesInteractor.getWeeklyTrendingMovies()
            when (result) {
                is ResultSuccess -> view?.showSearchResults(result.data.results)
                is ResultError -> view?.showSearchError()
            }
        }
    }

    override fun searchMovies(query: String) {
        launch {
            if (!validateSearchQuery(query)) {
                resetSearchParameters()//temp
                view?.showSearchError()
                return@launch
            }

            resetSearchParameters(query)

            val result = findMoviesInteractor.searchMovies(query)
            if (result is ResultSuccess) {
                searchPages = result.data.totalPages
                searchResults.clear()
                searchResults.addAll(result.data.results)
                view?.showSearchResults(result.data.results)
            } else if (result is ResultError) {
                view?.showSearchError()
            }
        }
    }

    override fun appendSearchResult() {
        launch {
            if (currentPage >= searchPages) return@launch

            searchQuery?.also { searchQuery ->
                currentPage++

                val result = findMoviesInteractor.searchMovies(searchQuery, currentPage)
                if (result is ResultSuccess) {
                    searchResults.addAll(result.data.results)
                    view?.appendSearchResults(result.data.results)
                } else if (result is ResultError) {
                    view?.showSearchError()
                }
            }
        }
    }

    override fun restoreSearchResults() {
        launch {
            searchQuery?.let {
                view?.showSearchResults(searchResults)
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