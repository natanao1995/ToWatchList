package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.BaseContract

interface FilmsListContract {
    interface Presenter : BaseContract.Presenter<FilmsListContract.View> {
        fun searchFilm(query: String)
    }

    interface View : BaseContract.View {
        fun showResultsOfSearch(result: String)
    }
}