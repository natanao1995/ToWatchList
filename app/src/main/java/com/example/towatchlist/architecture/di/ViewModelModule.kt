package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.find_movies.FindMoviesViewModel
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val presenterModule = module {
        viewModel { FindMoviesViewModel(get()) }
        viewModel { FoundMovieDetailsViewModel(get()) }
    }
}