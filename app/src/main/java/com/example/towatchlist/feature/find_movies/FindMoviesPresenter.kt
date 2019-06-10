package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.coroutines.launch

class FindMoviesPresenter(private val findMoviesInteractor: FindMoviesContract.Interactor) : FindMoviesContract.Presenter() {
    private var searchQuery: String? = null
    private var searchPages = 1
    private var currentPage = 1
    private val searchResults = ArrayList<SearchMovieResponseEntity.SearchMovieResponseResult>()

    override fun searchMovies(query: String) {
        launch {
            if (!validateSearchQuery(query)) {
                resetSearchParameters()//temp
                view?.showSearchError()
                return@launch
            }

            resetSearchParameters(query)

            val result = findMoviesInteractor.searchMovie(query)
            if (result is Result.Success) {
                searchPages = result.data.totalPages
                searchResults.clear()
                searchResults.addAll(result.data.results)
                view?.showSearchResults(result.data.results)
            } else if (result is Result.Error) {
                view?.showSearchError()
            }
        }
    }

    override fun appendSearchResult() {
        launch {
            if (currentPage >= searchPages) return@launch

            searchQuery?.also { searchQuery ->
                currentPage++

                val result = findMoviesInteractor.searchMovie(searchQuery, currentPage)
                if (result is Result.Success) {
                    searchResults.addAll(result.data.results)
                    view?.appendSearchResults(result.data.results)
                } else if (result is Result.Error) {
                    view?.showSearchError()
                }
            }
        }
    }

    override fun restoreSearchResults() {
        launch {
            searchQuery?.also {
                view?.showSearchResults(searchResults)
            }
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