package com.example.towatchlist.model.local

import com.example.towatchlist.model.local.entity.SavedMovie
import com.example.towatchlist.model.remote.entity.MovieListResultObject

fun MovieListResultObject.SearchMovieResponseResult.toDatabaseEntity(): SavedMovie {
    return SavedMovie(
        id,
        title,
        overview,
        posterPath,
        voteAverage)
}