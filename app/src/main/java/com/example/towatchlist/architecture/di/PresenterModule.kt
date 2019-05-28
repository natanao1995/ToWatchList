package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.find_movies.FindMoviesContract
import com.example.towatchlist.feature.find_movies.FindMoviesPresenter
import org.koin.dsl.module

object PresenterModule {
    val presenterModule = module {
        factory { FindMoviesPresenter(get()) as FindMoviesContract.Presenter }
    }
}