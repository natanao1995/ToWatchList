package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.BasePresenter
import com.example.towatchlist.model.remote.TMDbService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FilmsListPresenter : BasePresenter<FilmsListContract.View>(), FilmsListContract.Presenter {
    override fun searchFilm(query: String) {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val originalRequest = it.request()
                val originalUrl = originalRequest.url()

                val url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", "2564e6c3603b3fe852685889227b91ab")
                    .build()

                val request = originalRequest.newBuilder()
                    .url(url)
                    .build()

                return@addInterceptor it.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(TMDbService::class.java)

        GlobalScope.launch {
            val response = service.searchMovie("Harry").execute()
            withContext(Dispatchers.Main) {
                view?.showResultsOfSearch(response.toString())
            }
        }
    }
}