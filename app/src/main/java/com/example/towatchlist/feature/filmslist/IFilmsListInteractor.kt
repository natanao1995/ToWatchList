package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.Result

interface IFilmsListInteractor {
    suspend fun searchFilm(query: String): Result<String>
}