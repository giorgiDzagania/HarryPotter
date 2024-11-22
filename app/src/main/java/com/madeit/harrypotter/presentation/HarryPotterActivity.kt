package com.madeit.harrypotter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.madeit.harrypotter.R
import com.madeit.harrypotter.databinding.ActivityHarryPotterBinding
import com.madeit.harrypotter.extensions.switchNavGraph

class HarryPotterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHarryPotterBinding.inflate(layoutInflater) }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUp()
    }

    private fun setUp() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.btmMenu, navController)

        binding.btmMenu.setOnItemSelectedListener { menuItem ->
            navController.switchNavGraph(this, menuItem.itemId)
            true
        }
    }

}