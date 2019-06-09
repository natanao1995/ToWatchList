package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.architecture.base.MvpView
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity

interface FindMoviesContract {
    abstract class Presenter : BasePresenter<View>() {
        abstract fun searchMovies(query: String)
        abstract fun appendSearchResult()
        abstract fun restoreSearchResults()
    }

    interface View : MvpView {
        fun showSearchResults(result: List<SearchMovieResponseEntity.SearchMovieResponseResult>)
        fun appendSearchResults(result: List<SearchMovieResponseEntity.SearchMovieResponseResult>)
        fun showSearchError()
    }
}