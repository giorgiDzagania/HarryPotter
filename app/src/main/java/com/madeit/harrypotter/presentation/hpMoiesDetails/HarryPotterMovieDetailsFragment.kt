package com.madeit.harrypotter.presentation.hpMoiesDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.madeit.harrypotter.data.model.MovieData
import com.madeit.harrypotter.databinding.FragmentHarryPottterMovieDetailsBinding
import kotlinx.coroutines.launch

class HarryPotterMovieDetailsFragment : Fragment() {
    private val args by navArgs<HarryPotterMovieDetailsFragmentArgs>()
    private val hpMovieDetailsViewModel by viewModels<HpMovieDetailsViewModel>()
    private lateinit var binding: FragmentHarryPottterMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHarryPottterMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getIdOfCurrentMovie()
        collectBookDetails()
    }

    private fun getIdOfCurrentMovie() {
        val movieId = args.movieId
        if (movieId.isNullOrEmpty()) {
            Log.e("MovieDetailsFragment", "Movie ID is null or empty!")
        } else {
            Log.d("MovieDetailsFragment", "Received Movie ID: $movieId")
            hpMovieDetailsViewModel.getHpMovieDetailsById(movieId)
        }
    }

    private fun collectBookDetails() {
        lifecycleScope.launch {
            hpMovieDetailsViewModel.movieDetails.collect { movieData ->
                if (movieData != null) {
                    updateUI(movieData)
                } else {
                    Log.e("MovieDetailsFragment", "Movie data is null. Could not update UI.")
                }
            }
        }
    }

    private fun updateUI(movieData: MovieData) {
        with(binding) {
            Glide.with(movieImage)
                .load(movieData.attributes?.poster)
                .into(movieImage)

            movieTitle.text = movieData.attributes?.title ?: "Title not available"
            movieSummery.text = movieData.attributes?.summary ?: "Summary not available"
        }
    }


}