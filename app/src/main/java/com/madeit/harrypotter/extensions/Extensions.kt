package com.madeit.harrypotter.extensions

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import com.madeit.harrypotter.R

fun NavController.switchNavGraph(context: Context, itemId: Int){
    val navGraph: NavGraph = when (itemId) {
        R.id.hpBooks -> {
            navInflater.inflate(R.navigation.nav_graph)
        }
        R.id.hpMovies -> {
            navInflater.inflate(R.navigation.nav_graph_movies)
        }
        else -> {
            return
        }
    }
    val appName = context.getString(R.string.app_name)
    println("Navigating with app context: $appName")
    graph = navGraph
}