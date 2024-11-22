package com.madeit.harrypotter.presentation.hpBooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.madeit.harrypotter.databinding.FragmentHarryPotterBinding
import kotlinx.coroutines.launch

class HarryPotterFragment : Fragment() {
    private lateinit var binding: FragmentHarryPotterBinding
    private val hpAdapter = HarryPotterAdapter()
    private val hpViewModel by viewModels<HarryPotterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHarryPotterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        setCollectors()
        goToDetailsFragment()
    }

    private fun initRecyclerView() {
        binding.recyclerViewBooks.apply {
            adapter = hpAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setCollectors() {
        lifecycleScope.launch {
            hpViewModel.hpBooks.collect {
                hpAdapter.updateBooksList(it)
            }
        }
    }

    private fun goToDetailsFragment() {
        hpAdapter.onItemClick = { bookData ->
            findNavController().navigate(
                HarryPotterFragmentDirections.actionHarryPotterFragmentToDetailsFragment(
                    "${bookData.id}"))
        }
    }

}