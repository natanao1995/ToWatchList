package com.example.towatchlist.model.local

import com.example.towatchlist.model.local.entity.SavedMovie
import com.example.towatchlist.model.remote.entity.SearchMovieResponseEntity

fun SearchMovieResponseEntity.SearchMovieResponseResult.toDatabaseEntity(): SavedMovie {
    return SavedMovie(
        id,
        title,
        overview,
        posterPath,
        voteAverage)
}