package com.example.moviebrowse.ui.theme.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.moviebrowse.data.api.Movie

@Composable
fun MovieListScreen(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    onToggleFavorite: (Movie) -> Unit
) {
    if (movies.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn {
            items(movies) { movie ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onMovieClick(movie) }
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                            contentDescription = movie.title,
                            modifier = Modifier
                                .size(100.dp)
                        )
                        Column(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        ) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = movie.overview,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        IconButton(
                            onClick = { onToggleFavorite(movie) },
                            modifier = Modifier.align(Alignment.Top)
                        ) {
                            Icon(
                                imageVector = if (movie.isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                                contentDescription = if (movie.isFavorite) "Remove from favorites" else "Add to favorites"
                            )
                        }
                    }
                }
            }
        }
    }
}

