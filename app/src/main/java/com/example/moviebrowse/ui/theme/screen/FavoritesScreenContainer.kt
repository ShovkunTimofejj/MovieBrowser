package com.example.moviebrowse.ui.theme.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.moviebrowse.viewmodel.MovieViewModel

@Composable
fun FavoritesScreenContainer(viewModel: MovieViewModel) {
    val favorites = viewModel.favorites.collectAsState()

    FavoritesScreen(
        favorites = favorites.value,
        onMovieClick = { movie ->
        }
    )
}