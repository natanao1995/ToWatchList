package com.example.towatchlist.feature.found_movie_details

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.architecture.base.MvpView

interface FoundMovieDetailsContract {
    abstract class Presenter : BasePresenter<View>()

    interface View : MvpView

    interface Interactor
}