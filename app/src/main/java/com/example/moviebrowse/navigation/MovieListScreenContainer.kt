package com.example.moviebrowse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.moviebrowse.ui.theme.screen.MovieListScreen
import com.example.moviebrowse.viewmodel.MovieViewModel

@Composable
fun MovieListScreenContainer(viewModel: MovieViewModel, navController: NavHostController) {
    val movies = viewModel.movies.collectAsState()
    MovieListScreen(
        movies = movies.value,
        onMovieClick = { movie ->
             navController.navigate(Screen.Details.createRoute(movie.id))
        },
        onToggleFavorite = { movie ->
            viewModel.toggleFavorite(movie)
        }
    )
}
