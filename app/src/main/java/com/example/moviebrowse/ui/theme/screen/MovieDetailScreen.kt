package com.example.moviebrowse.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviebrowse.data.api.Movie
import com.example.moviebrowse.viewmodel.MovieViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MovieDetailScreen(movie: Movie, viewModel: MovieViewModel, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
            contentDescription = movie.title,
            modifier = Modifier.fillMaxWidth().height(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Release Date: ${movie.release_date}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)

        IconButton(onClick = {
            viewModel.toggleFavorite(movie)
        }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Add to Favorites"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailPreview() {
    MovieDetailScreen(
        movie = Movie(
            id = 1,
            title = "Sample Movie",
            poster_path = "/path/to/poster.jpg",
            overview = "This is a sample movie overview.",
            release_date = "2023-04-01"
        ),
        viewModel = MovieViewModel(viewModel()),
        navController = rememberNavController()
    )
}

