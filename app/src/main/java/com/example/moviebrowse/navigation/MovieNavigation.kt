package com.example.moviebrowse.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviebrowse.ui.theme.screen.FavoritesScreenContainer
import com.example.moviebrowse.viewmodel.MovieViewModel

@Composable
fun MovieNavigation(viewModel: MovieViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {
            MovieListScreenContainer(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreenContainer(viewModel = viewModel)
        }
    }
}
