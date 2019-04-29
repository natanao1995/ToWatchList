package com.example.towatchlist.architecture.di

import com.example.towatchlist.model.remote.TMDbService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    val networkModule = module {
        single { createOkHttpClient() }
        single { createRetrofitInstance(get()) }
        single { createTMDbService(get()) }
    }

    private fun createOkHttpClient(): OkHttpClient {
        val apiKeyInterceptor = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url()

                val url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", "2564e6c3603b3fe852685889227b91ab")
                    .build()

                val request = originalRequest.newBuilder()
                    .url(url)
                    .build()

                return chain.proceed(request)
            }
        }
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun createRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    private fun createTMDbService(retrofit: Retrofit): TMDbService {
        return retrofit.create(TMDbService::class.java)
    }
}