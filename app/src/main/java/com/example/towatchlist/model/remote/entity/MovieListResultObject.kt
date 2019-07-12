package com.example.towatchlist.model.remote.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/** https://developers.themoviedb.org/3/search/search-movies */
data class MovieListResultObject(
    val page: Int,
    val results: List<SearchMovieResponseResult>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
) {
    @Parcelize
    data class SearchMovieResponseResult(
        @SerializedName("poster_path")
        val posterPath: String?,
        val adult: Boolean,
        val overview: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("genre_ids")
        val genreIds: List<Int>,
        val id: Int,
        @SerializedName("original_title")
        val originalTitle: String,
        @SerializedName("original_language")
        val originalLanguage: String,
        val title: String,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        val popularity: Double,
        @SerializedName("vote_count")
        val voteCount: Int,
        val video: Boolean,
        @SerializedName("vote_average")
        val voteAverage: Double
    ) : Parcelable
}