package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.find_movies.FindMoviesContract
import com.example.towatchlist.feature.find_movies.FindMoviesPresenter
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsContract
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsPresenter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresenterModule {
    val presenterModule = module {
        viewModel { FindMoviesPresenter(get()) as FindMoviesContract.Presenter }
        viewModel { FoundMovieDetailsPresenter(get()) as FoundMovieDetailsContract.Presenter }
    }
}