package com.example.towatchlist.architecture.di.module

import android.app.Activity
import com.example.towatchlist.feature.filmslist.FilmsListContract
import com.example.towatchlist.feature.filmslist.FilmsListPresenter
import dagger.Module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

@Module
class ActivityModule(private var activity: Activity) {
    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): FilmsListContract.Presenter {
        return FilmsListPresenter()
    }
}