package com.example.towatchlist.architecture.di.component

import com.example.towatchlist.architecture.di.module.ActivityModule
import com.example.towatchlist.feature.filmslist.FilmsListActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(filmsListActivity: FilmsListActivity)
}