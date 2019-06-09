package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.find_movies.FindMoviesContract
import com.example.towatchlist.feature.find_movies.FindMoviesPresenter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresenterModule {
    val presenterModule = module {
        viewModel { FindMoviesPresenter(get()) as FindMoviesContract.Presenter }
    }
}