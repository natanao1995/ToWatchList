package com.example.towatchlist.model.remote.entity

data class GenresResponseEntity(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}