package com.madeit.harrypotter.data

import com.madeit.harrypotter.data.model.BookDataWrapper
import com.madeit.harrypotter.data.model.HarryPotterAllMoviesList
import com.madeit.harrypotter.data.model.HarryPotterList
import com.madeit.harrypotter.data.model.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HarryPotterApi {

    @GET("books")
    suspend fun getHarryPotterBooks(@Query("id") id: String = ""): Response<HarryPotterList>

    @GET("books/{id}")
    suspend fun getBookDetails(@Path("id") id: String): Response<BookDataWrapper>

    // For Movies
    @GET("movies")
    suspend fun getHarryPotterMovies(): Response<HarryPotterAllMoviesList>

    @GET("movies/{id}")
    suspend fun getHpMovieDetailsById(@Path("id") id: String): Response<MovieDetails>
}

