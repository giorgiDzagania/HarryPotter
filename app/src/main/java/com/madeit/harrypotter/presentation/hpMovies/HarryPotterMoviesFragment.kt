package com.madeit.harrypotter.presentation.hpMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madeit.harrypotter.databinding.FragmentHarryPotterMoviesBinding
import kotlinx.coroutines.launch

class HarryPotterMoviesFragment : Fragment() {
    private lateinit var binding: FragmentHarryPotterMoviesBinding
    private val hpMoviesAdapter = HpMoviesAdapter()
    private val hpMoviesViewModel by viewModels<HpMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHarryPotterMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setCollectors()
        goToMovieDetailsFragment()
    }

    private fun goToMovieDetailsFragment() {
        hpMoviesAdapter.onDetailBtnClick = { movie ->
            findNavController().navigate(
                HarryPotterMoviesFragmentDirections.actionHarryPotterMoviesFragmentToHarryPotterMovieDetailsFragment(
                    movie.id ?: ""))
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            hpMoviesViewModel.harryPotterAllMovies.collect { listOfMovies ->
                hpMoviesAdapter.updateMoviesList(listOfMovies)
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewMovies.apply {
            adapter = hpMoviesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

}