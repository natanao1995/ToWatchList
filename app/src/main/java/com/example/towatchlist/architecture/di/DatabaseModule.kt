package com.example.towatchlist.architecture.di

import android.content.Context
import androidx.room.Room
import com.example.towatchlist.Constants.DATABASE_NAME
import com.example.towatchlist.model.local.AppDatabase
import com.example.towatchlist.model.local.dao.SavedMovieDao
import org.koin.dsl.module

object DatabaseModule {
    val databaseModule = module {
        single { createAppDatabase(get()) }
        single { createSavedMovieDao(get()) }
    }

    private fun createAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    private fun createSavedMovieDao(appDatabase: AppDatabase): SavedMovieDao {
        return appDatabase.savedMovieDao()
    }
}