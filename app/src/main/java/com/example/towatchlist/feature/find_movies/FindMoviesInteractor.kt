package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.local.dao.SavedMovieDao
import com.example.towatchlist.model.remote.TMDbService
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import com.example.towatchlist.model.remote.entity.ErrorResponseEntity
import com.google.gson.Gson

class FindMoviesInteractor(
    private val tmDbService: TMDbService,
    private val savedMovieDao: SavedMovieDao
) : IFindMoviesInteractor {

     override suspend fun searchMovie(query: String, page: Int?): Result<SearchMovieResponseEntity> {
        try {
            val result = tmDbService.searchMovieAsync(query, page)
            if (result.isSuccessful) {
                result.body()?.let {
                    return Result.Success(it)
                }
            } else {
                return handleErrorResponse(result.errorBody()?.string())
            }
        } catch (exception: Exception) {
            return Result.Error(exception)
        }
        return Result.Error(Exception("Not handled error while send message"))
    }

    private fun handleErrorResponse(responseString: String?): Result.Error {
        val errorEntity = Gson().fromJson(responseString, ErrorResponseEntity::class.java)
        return Result.Error(Exception(errorEntity?.statusMessage))
    }
}