package com.example.towatchlist.architecture

import android.app.Application
import com.example.towatchlist.architecture.di.component.ApplicationComponent
import com.example.towatchlist.architecture.di.component.DaggerApplicationComponent
import com.example.towatchlist.architecture.di.module.ApplicationModule

class ToWatchListApplication : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: ToWatchListApplication private set
    }
}