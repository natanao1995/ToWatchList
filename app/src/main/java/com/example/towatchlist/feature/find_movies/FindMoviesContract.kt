package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.architecture.base.MvpView
import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.entity.MovieListResultObject

interface FindMoviesContract {
    abstract class Presenter : BasePresenter<View>() {
        abstract fun getWeeklyTrendingMovies()
        abstract fun searchMovies(query: String)
        abstract fun appendSearchResult()
        abstract fun restoreSearchResults()
    }

    interface View : MvpView {
        fun showSearchResults(result: List<MovieListResultObject.SearchMovieResponseResult>)
        fun appendSearchResults(result: List<MovieListResultObject.SearchMovieResponseResult>)
        fun showSearchError()
    }

    interface Interactor {
        suspend fun searchMovies(query: String, page: Int? = null): Result<MovieListResultObject>
        suspend fun getWeeklyTrendingMovies(): Result<MovieListResultObject>
    }
}