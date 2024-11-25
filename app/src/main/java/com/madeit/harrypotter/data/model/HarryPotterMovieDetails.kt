package com.madeit.harrypotter.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(
    val data: MovieData?
)

data class MovieData(
    val id: String?,
    val attributes: MovieAttribute?
)

data class MovieAttribute(
    val poster: String?,
    val rating: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val summary: String?,
    val title: String?
)