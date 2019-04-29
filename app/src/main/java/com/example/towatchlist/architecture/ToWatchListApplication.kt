package com.example.towatchlist.architecture

import android.app.Application
import com.example.towatchlist.architecture.di.InteractorModule.interactorModule
import com.example.towatchlist.architecture.di.NetworkModule.networkModule
import com.example.towatchlist.architecture.di.PresenterModule.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ToWatchListApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ToWatchListApplication)

            modules(presenterModule, interactorModule, networkModule)
        }
    }
}