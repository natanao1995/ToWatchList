package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity

interface IFilmsListInteractor {
    suspend fun searchFilm(query: String): Result<SearchMovieResponseEntity>
}