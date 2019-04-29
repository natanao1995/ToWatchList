package com.example.towatchlist.architecture.di.component

import com.example.towatchlist.architecture.ToWatchListApplication
import com.example.towatchlist.architecture.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: ToWatchListApplication)
}