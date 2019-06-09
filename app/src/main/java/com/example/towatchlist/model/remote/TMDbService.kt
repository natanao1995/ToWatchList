package com.example.towatchlist.model.remote

import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {
    @GET("search/movie")
    suspend fun searchMovieAsync(@Query("query") query: String): Response<SearchMovieResponseEntity>
}