package com.madeit.harrypotter.presentation.hpMovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeit.harrypotter.data.model.Movies
import com.madeit.harrypotter.data.repository.HarryPotterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HpMovieViewModel : ViewModel() {
    private val harryPotterRepository = HarryPotterRepository()

    private var _harryPotterAllMovies = MutableStateFlow<List<Movies>>(emptyList())
    val harryPotterAllMovies: StateFlow<List<Movies>> = _harryPotterAllMovies

    init {
        getAllHarryPotterMovies()
    }

    private fun getAllHarryPotterMovies() = viewModelScope.launch {
        val allHpMovies = harryPotterRepository.getAllHarryPotterMovies()
        if (allHpMovies != null) {
            _harryPotterAllMovies.emit(allHpMovies)
        }
    }

}