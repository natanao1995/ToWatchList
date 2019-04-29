package com.example.towatchlist.feature.filmslist

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.TMDbService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilmsListInteractor(private val tmDbService: TMDbService): IFilmsListInteractor {
    override suspend fun searchFilm(query: String): Result<String> = withContext(Dispatchers.IO) {
        return@withContext Result.Success(tmDbService.searchMovie("Harry").execute().toString())
    }
}