package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.filmslist.FilmsListInteractor
import com.example.towatchlist.feature.filmslist.IFilmsListInteractor
import org.koin.dsl.module

object InteractorModule {
    val interactorModule = module {
        factory { FilmsListInteractor(get()) as IFilmsListInteractor }
    }
}