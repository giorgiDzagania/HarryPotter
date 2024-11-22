package com.madeit.harrypotter.data.model

import com.google.gson.annotations.SerializedName

data class HarryMovies(
    val data: List<Movie>,
)

data class Movie(
    val id: String?,
    val attributes: MovieAttributes?
)

data class MovieAttributes(
    val title: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val rating: String?,
    val poster: String?,
    val summary: String? = ""
)