package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.BaseContract
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity

interface FindMoviesContract {
    interface Presenter : BaseContract.Presenter<FindMoviesContract.View> {
        fun searchMovie(query: String)
    }

    interface View : BaseContract.View {
        fun showSearchResults(result: List<SearchMovieResponseEntity.SearchMovieResponseResult>)
        fun showSearchError()
    }
}