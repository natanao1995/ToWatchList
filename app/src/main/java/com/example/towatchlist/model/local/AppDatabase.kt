package com.example.towatchlist.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.towatchlist.model.local.dao.GenreDao
import com.example.towatchlist.model.local.dao.SavedMovieDao
import com.example.towatchlist.model.local.entity.Genre
import com.example.towatchlist.model.local.entity.SavedMovie

@Database(entities = [
    SavedMovie::class,
    Genre::class
], version = 1)
@TypeConverters(Genre.TranslationsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun savedMovieDao(): SavedMovieDao
    abstract fun genreDao(): GenreDao
}