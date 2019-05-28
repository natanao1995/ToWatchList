package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.remote.TMDbService
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class FindMoviesInteractor(
    private val tmDbService: TMDbService
) : IFindMoviesInteractor {
    override suspend fun searchMovie(query: String)
            : Result<SearchMovieResponseEntity> = withContext(Dispatchers.IO) {
        return@withContext try {
            Result.Success(tmDbService.searchMovieAsync(query).await())
        } catch (httpException: HttpException) {
            Result.Error(httpException)
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }
}