package com.example.towatchlist.model.remote

import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbService {
    @GET("search/movie")
    fun searchMovieAsync(@Query("query") query: String): Deferred<SearchMovieResponseEntity>
}