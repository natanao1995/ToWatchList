package com.example.towatchlist.architecture.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.towatchlist.Constants.DATABASE_NAME
import com.example.towatchlist.model.local.AppDatabase
import org.koin.dsl.module

object DatabaseModule {
    val databaseModule = module {
        single { createAppDatabase(get()) }
    }

    private fun createAppDatabase(applicationContext: Context): RoomDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }
}