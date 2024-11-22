package com.madeit.harrypotter.data.model

import com.google.gson.annotations.SerializedName

data class BookAttributes(
    val title: String?,
    val author: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    val summary: String? = "",
    @SerializedName("cover")
    val coverUrl: String?
)

data class BookData(
    val id: String?,
    val attributes: BookAttributes?
)

data class BookDataWrapper(
    val data: BookData?
)

data class HarryPotterList(
    val data: List<BookData>
)