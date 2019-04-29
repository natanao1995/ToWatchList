package com.example.towatchlist.model.remote

import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {
    @GET("search/movie")
    fun searchMovie(@Query("query") query: String): Call<SearchMovieResponseEntity>
}