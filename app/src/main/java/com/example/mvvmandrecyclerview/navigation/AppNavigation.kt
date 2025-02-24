package com.example.mvvmandrecyclerview.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.toRoute
import com.example.mvvmandrecyclerview.ui.details.FilmDetailsScreenWithViewModel
import com.example.mvvmandrecyclerview.ui.main.FilmListScreenWithViewModel
import kotlinx.serialization.Serializable

@Serializable
object FilmList
@Serializable
data class FilmDetails(val id: Int = 0)

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = FilmList) {
        composable<FilmList> {
            FilmListScreenWithViewModel { id ->
                navController.navigate(FilmDetails(id))
            }
        }
        composable<FilmDetails> { backStackEntry ->
            val filmDetails: FilmDetails = backStackEntry.toRoute()
            FilmDetailsScreenWithViewModel(filmId = filmDetails.id)
        }
    }
}
