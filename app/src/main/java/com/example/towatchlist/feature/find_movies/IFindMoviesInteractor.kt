package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.entity.MovieListResultObject

interface IFindMoviesInteractor {
    suspend fun searchMovies(query: String, page: Int? = null): Result<MovieListResultObject>
    suspend fun getWeeklyTrendingMovies(): Result<MovieListResultObject>
}