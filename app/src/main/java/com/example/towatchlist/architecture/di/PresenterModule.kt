package com.example.towatchlist.architecture.di

import com.example.towatchlist.feature.filmslist.FilmsListContract
import com.example.towatchlist.feature.filmslist.FilmsListPresenter
import org.koin.dsl.module

object PresenterModule {
    val presenterModule = module {
        factory { FilmsListPresenter(get()) as FilmsListContract.Presenter }
    }
}