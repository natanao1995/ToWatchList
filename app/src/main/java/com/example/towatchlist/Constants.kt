package com.example.towatchlist

object Constants {
    const val BASE_URL_TMDB = "https://api.themoviedb.org/3/"
    const val BASE_URL_TMDB_IMAGE = "https://image.tmdb.org/t/p/w500"
    const val API_KEY_TMDB = "2564e6c3603b3fe852685889227b91ab"

    const val DATABASE_NAME = "to_watch_list_database"

    private const val ITEMS_PER_PAGE = 20
    const val ITEMS_BEFORE_PAGINATION = (ITEMS_PER_PAGE * 0.75).toInt()
}