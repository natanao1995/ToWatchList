package com.example.towatchlist.feature.find_movies

import com.example.towatchlist.architecture.base.BaseInteractor
import com.example.towatchlist.architecture.base.Result
import com.example.towatchlist.model.local.dao.SavedMovieDao
import com.example.towatchlist.model.remote.TMDbService
import com.example.towatchlist.model.remote.entity.MovieListResultObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FindMoviesInteractor(
    private val tmDbService: TMDbService,
    private val savedMovieDao: SavedMovieDao
) : BaseInteractor(), FindMoviesContract.Interactor {

    override suspend fun searchMovies(query: String, page: Int?)
            : Result<MovieListResultObject> = withContext(Dispatchers.IO) {
        processRequest {
            tmDbService.searchMoviesAsync(query, page)
        }
    }

    override suspend fun getWeeklyTrendingMovies()
            : Result<MovieListResultObject> = withContext(Dispatchers.IO){
        processRequest {
            tmDbService.getWeeklyTrendingMoviesAsync()
        }
    }
}