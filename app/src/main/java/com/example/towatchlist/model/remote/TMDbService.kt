package com.example.towatchlist.model.remote

import com.example.towatchlist.model.remote.entity.MovieListResultObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {
    @GET("search/movie")
    suspend fun searchMoviesAsync(
        @Query("query") query: String,
        @Query("page") page: Int? = null
    ): Response<MovieListResultObject>

    @GET("trending/movie/week")
    suspend fun getWeeklyTrendingMoviesAsync(): Response<MovieListResultObject>
}