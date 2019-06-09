package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.find_movies.FindMoviesInteractor
import com.example.towatchlist.feature.find_movies.IFindMoviesInteractor
import org.koin.dsl.module

object InteractorModule {
    val interactorModule = module {
        factory { FindMoviesInteractor(get(), get()) as IFindMoviesInteractor }
    }
}