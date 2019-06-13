package com.example.towatchlist.architecture.di

import com.example.towatchlist.Constants.API_KEY_TMDB
import com.example.towatchlist.Constants.BASE_URL_TMDB
import com.example.towatchlist.model.remote.TMDbService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

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
                    .addQueryParameter("api_key", API_KEY_TMDB)
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
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    private fun createRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_TMDB)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun createTMDbService(retrofit: Retrofit): TMDbService {
        return retrofit.create(TMDbService::class.java)
    }
}