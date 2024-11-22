package com.madeit.harrypotter.presentation.hpBooksDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.madeit.harrypotter.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {
    private val args by navArgs<DetailsFragmentArgs>()
    private val detailsViewModel by viewModels<DetailsViewModel>()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getIdOfCurrentBook()
        collectBookDetails()
    }


    private fun getIdOfCurrentBook() {
        val curBookId = args.id
        detailsViewModel.getBookDetails(curBookId)
    }

    private fun collectBookDetails() {
        lifecycleScope.launch {
            displayBookInfo()
        }
    }

    private suspend fun displayBookInfo(){
        detailsViewModel.bookDetails.collect { bookData ->
            bookData?.let {
                with(binding){
                    Glide.with(bookImage)
                        .load(it.attributes?.coverUrl)
                        .into(bookImage)
                    bookTitle.text = it.attributes?.title ?: "ERROR"
                    binding.bookSummery.text = it.attributes?.summary ?: ""
                }
            }
        }
    }

}