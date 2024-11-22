package com.madeit.harrypotter.presentation.hpBooksDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.madeit.harrypotter.data.model.BookData
import com.madeit.harrypotter.data.repository.HarryPotterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository = HarryPotterRepository()

    private val _bookDetails = MutableStateFlow<BookData?>(null)
    val bookDetails: StateFlow<BookData?> = _bookDetails

    fun getBookDetails(bookId: String) = viewModelScope.launch {
        val book = repository.getBookById(bookId = bookId)
        _bookDetails.emit(book)
    }

}