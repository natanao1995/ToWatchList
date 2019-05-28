package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity

interface IFindMoviesInteractor {
    suspend fun searchMovie(query: String): Result<SearchMovieResponseEntity>
}