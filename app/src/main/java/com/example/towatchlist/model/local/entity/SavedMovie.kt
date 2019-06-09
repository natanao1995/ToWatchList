package com.example.towatchlist.model.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_movies")
data class SavedMovie(
    @PrimaryKey val movie_id: Int,

    val title: String,

    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double
)