package com.example.towatchlist.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.towatchlist.model.local.entity.SavedMovie

@Dao
interface SavedMovieDao {
    @Query("SELECT * FROM saved_movies")
    fun getAll(): List<SavedMovie>

    @Insert
    fun insertAll(vararg movies: SavedMovie)

    @Delete
    fun delete(movie: SavedMovie)
}