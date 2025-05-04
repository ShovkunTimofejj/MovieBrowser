package com.example.moviebrowse.ui.theme.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import com.example.moviebrowse.data.db.FavoriteMovie
import coil.compose.AsyncImage

@Composable
fun FavoritesScreen(favorites: List<FavoriteMovie>, onMovieClick: (FavoriteMovie) -> Unit) {
    LazyColumn {
        items(favorites) { movie ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMovieClick(movie) }
            ) {
                Row(Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        contentDescription = movie.title,
                        modifier = Modifier.size(100.dp)
                    )
                    Column(Modifier.padding(start = 8.dp)) {
                        Text(movie.title, style = MaterialTheme.typography.titleMedium)
                        Text(movie.overview, maxLines = 2)
                    }
                }
            }
        }
    }
}