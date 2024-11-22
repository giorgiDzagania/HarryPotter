package com.madeit.harrypotter.presentation.hpBooks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeit.harrypotter.data.model.BookData
import com.madeit.harrypotter.data.repository.HarryPotterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HarryPotterViewModel : ViewModel() {
    private val allBooksRepository = HarryPotterRepository()

    private var _hpBooks = MutableStateFlow<List<BookData>>(emptyList())
    val hpBooks: StateFlow<List<BookData>> = _hpBooks

    init {
        getAllBooks()
    }

    private fun getAllBooks() = viewModelScope.launch {
        val book = allBooksRepository.getAllHPBooks()
        if (book != null) {
            _hpBooks.emit(book)
        } else {
            Log.d("HarryPotterViewModel", "hasn't found Book")
        }
    }

}