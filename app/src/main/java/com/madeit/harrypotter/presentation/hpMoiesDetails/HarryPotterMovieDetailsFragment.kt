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
        val curMovie = args.movieId
        if (curMovie.isNullOrEmpty()) {
            Log.e("MovieDetailsFragment", "Movie ID is null or empty!")
        } else {
            Log.d("MovieDetailsFragment", "Received Movie ID: $curMovie")
            hpMovieDetailsViewModel.getHpMovieDetailsById(curMovie)
        }
    }

    private fun collectBookDetails(){
        lifecycleScope.launch{
           displayBookInfo()
        }
    }

    private suspend fun displayBookInfo(){
        hpMovieDetailsViewModel.movieDetails.collect { movieData ->
            movieData?.let {
                with(binding){
                    Glide.with(movieImage)
                        .load(it.attributes?.poster)
                        .into(movieImage)
                    movieTitle.text = it.attributes?.title
                    movieSummery.text = it.attributes?.summary
                }
            }
        }
    }


}