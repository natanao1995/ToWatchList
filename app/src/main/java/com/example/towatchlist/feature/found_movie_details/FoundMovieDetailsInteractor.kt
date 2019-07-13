package com.example.towatchlist.feature.found_movie_details

import com.example.towatchlist.model.local.dao.SavedMovieDao
import com.example.towatchlist.model.remote.TMDbService

class FoundMovieDetailsInteractor(
    private val tmDbService: TMDbService,
    private val savedMovieDao: SavedMovieDao
) : IFoundMovieDetailsInteractor