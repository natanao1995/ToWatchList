package com.example.towatchlist.architecture.di.module

import android.app.Application
import com.example.towatchlist.architecture.ToWatchListApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ToWatchListApplication) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }
}