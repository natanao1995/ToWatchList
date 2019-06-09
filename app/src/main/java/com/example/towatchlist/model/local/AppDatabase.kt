package com.example.towatchlist.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.towatchlist.model.local.dao.SavedMovieDao
import com.example.towatchlist.model.local.entity.SavedMovie

@Database(entities = [SavedMovie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedMovieDao(): SavedMovieDao
}