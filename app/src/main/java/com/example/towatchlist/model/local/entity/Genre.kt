package com.example.towatchlist.model.local.entity

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "genres")
data class Genre(
    @ColumnInfo(name = "genre_id")
    @PrimaryKey
    val genreId: Int,

    val translations: Map<String, String>
) {
    class TranslationsConverter {
        @TypeConverter
        fun fromTranslations(translations: Map<String, String>): String {
            return Gson().toJson(translations)
        }

        @TypeConverter
        fun toTranslations(string: String): Map<String, String> {
            return Gson().fromJson(string, object : TypeToken<Map<String, String>>() {}.type)
        }
    }
}