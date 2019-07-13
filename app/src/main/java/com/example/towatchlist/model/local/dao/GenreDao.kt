package com.example.towatchlist.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.towatchlist.model.local.entity.Genre
import com.example.towatchlist.model.local.entity.SavedMovie

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres")
    fun getAll(): List<Genre>

    @Insert
    fun insertAll(vararg genres: Genre)

    @Delete
    fun delete(genre: Genre)
}