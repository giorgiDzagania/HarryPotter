package com.madeit.harrypotter.presentation.hpMoiesDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeit.harrypotter.data.model.Movie
import com.madeit.harrypotter.data.repository.HarryPotterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HpMovieDetailsViewModel : ViewModel() {
    private val harryPotterRepository = HarryPotterRepository()

    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> = _movieDetails

    fun getHpMovieDetailsById(movieId: String) {
        Log.d("MovieDetailsViewModel", "Fetching details for movie ID: $movieId")
        viewModelScope.launch {
            val movieDetails = harryPotterRepository.getHpMovieDetails(movieId)
            if (movieDetails != null) {
                Log.d("MovieDetailsViewModel", "Movie details fetched: $movieDetails")
                _movieDetails.emit(movieDetails)
            } else {
                Log.e("MovieDetailsViewModel", "Movie details could not be fetched for ID: $movieId")
            }
        }
    }

}