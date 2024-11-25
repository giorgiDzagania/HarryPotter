package com.madeit.harrypotter.data.repository

import android.util.Log
import com.madeit.harrypotter.data.RetrofitInstance
import com.madeit.harrypotter.data.model.Attributes
import com.madeit.harrypotter.data.model.BookAttributes
import com.madeit.harrypotter.data.model.BookData
import com.madeit.harrypotter.data.model.MovieData
import com.madeit.harrypotter.data.model.Movies

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

    suspend fun getAllHarryPotterMovies(): List<Movies>? {
        return try {
            val response = api.getHarryPotterMovies()
            if (response.isSuccessful) {
                response.body()?.data?.map { movie ->
                    Movies(
                        id = movie.id,
                        attributes = Attributes(
                            title = movie.attributes?.title,
                            poster = movie.attributes?.poster,
                            rating = movie.attributes?.rating,
                            releaseDate = movie.attributes?.releaseDate
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

    suspend fun getHpMovieDetails(movieId: String): MovieData? {
        return try {
            val response = api.getHpMovieDetailsById(id = movieId)
            if (response.isSuccessful) {
                val apiResponse = response.body()
                apiResponse?.data
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }


}