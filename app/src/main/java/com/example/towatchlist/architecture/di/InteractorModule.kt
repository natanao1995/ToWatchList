package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.find_movies.FindMoviesContract
import com.example.towatchlist.feature.find_movies.FindMoviesInteractor
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsContract
import com.example.towatchlist.feature.found_movie_details.FoundMovieDetailsInteractor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object InteractorModule {
    val interactorModule = module {
        factory { FindMoviesInteractor(androidContext(), get(), get()) as FindMoviesContract.Interactor }
        factory { FoundMovieDetailsInteractor(get(), get()) as FoundMovieDetailsContract.Interactor }
    }
}