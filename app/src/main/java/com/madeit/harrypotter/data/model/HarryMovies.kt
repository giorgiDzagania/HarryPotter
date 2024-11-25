package com.madeit.harrypotter.data.model

import com.google.gson.annotations.SerializedName

data class HarryPotterAllMoviesList(
    val data: List<Movies>,
)

data class Movies(
    val id: String?,
    val attributes: Attributes?,
)

data class Attributes(
    val title: String?,
    val poster: String?,
    val rating: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
)