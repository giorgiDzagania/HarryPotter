package com.madeit.harrypotter.data.repository

import android.util.Log
import com.madeit.harrypotter.data.RetrofitInstance
import com.madeit.harrypotter.data.model.BookAttributes
import com.madeit.harrypotter.data.model.BookData
import com.madeit.harrypotter.data.model.HarryMovies
import com.madeit.harrypotter.data.model.Movie
import com.madeit.harrypotter.data.model.MovieAttributes

class HarryPotterRepository {

    private val api = RetrofitInstance.createAllHarryPotterBooksApi()

    suspend fun getAllHPBooks(): List<BookData>? {
        return try {
            val response = api.getHarryPotterBooks()
            if (response.isSuccessful) {
                Log.d("HarryPotterRepository", "API Response: ${response.body()}")
                response.body()?.data?.map { bookData ->
                    BookData(
                        id = bookData.id,
                        attributes = BookAttributes(
                            title = bookData.attributes?.title,
                            author = bookData.attributes?.author,
                            releaseDate = bookData.attributes?.releaseDate,
                            summary = bookData.attributes?.summary,
                            coverUrl = bookData.attributes?.coverUrl
                        )
                    )
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getBookById(bookId: String): BookData? {
        return try {
            val response = api.getBookDetails(bookId)
            if (response.isSuccessful) {
                val bookData = response.body()?.data
                bookData
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAllHarryPotterMovies(): List<Movie>? {
        return try {
            val response = api.getHarryPotterMovies()
            if (response.isSuccessful) {
                // Safely map the response data to Movie objects
                response.body()?.data?.map { movie ->
                    Movie(
                        id = movie.id,
                        attributes = MovieAttributes(
                            title = movie.attributes?.title,
                            releaseDate = movie.attributes?.releaseDate,
                            rating = movie.attributes?.rating,
                            poster = movie.attributes?.poster,
                            summary = movie.attributes?.summary
                        )
                    )
                }
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getHpMovieDetails(movieId: String): Movie? {
        return try {
            val response = api.getHpMovieDetailsById(movieId)
            if (response.isSuccessful) {
                response.body() // Accessing `attributes` here
            } else {
                Log.e("MovieRepository", "Error: ${response.message()}")
                null
            }
        } catch (e: Exception) {
            Log.e("MovieRepository", "Exception: ${e.message}")
            null
        }
    }


}