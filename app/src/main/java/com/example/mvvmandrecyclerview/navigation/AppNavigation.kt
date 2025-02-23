package com.example.mvvmandrecyclerview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mvvmandrecyclerview.ui.details.FilmDetailsScreenWithViewModel
import com.example.mvvmandrecyclerview.ui.main.FilmListScreenWithViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "filmList") {
        composable("filmList") {
            FilmListScreenWithViewModel { id ->
                navController.navigate("filmDetails/${id}")
            }
        }
        composable("filmDetails/{filmId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("filmId")?.toInt() ?: 0
            FilmDetailsScreenWithViewModel(filmId = id)
        }
    }
}
