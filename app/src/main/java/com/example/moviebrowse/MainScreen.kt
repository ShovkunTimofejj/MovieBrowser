package com.example.moviebrowse

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.moviebrowse.navigation.Screen
import com.example.moviebrowse.ui.theme.screen.MovieListScreen
import com.example.moviebrowse.ui.theme.screen.MovieDetailScreen
import com.example.moviebrowse.viewmodel.MovieViewModel

@Composable
fun MainScreen(viewModel: MovieViewModel, apiKey: String) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf(0) }

    val items = listOf(Screen.Home, Screen.Favorites)

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = if (screen is Screen.Home) Icons.Default.Home
                                else Icons.Default.Star,
                                contentDescription = screen.route
                            )
                        },
                        label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                LaunchedEffect(Unit) {
                    Log.d("MainScreen", "Loading popular movies...")
                    viewModel.loadPopularMovies(apiKey)
                }

                val movies by viewModel.movies.collectAsState()

                LaunchedEffect(movies) {
                    Log.d("MainScreen", "Movies received: ${movies.size}")
                    movies.forEach { movie ->
                        Log.d("MainScreen", "Movie: ${movie.title}")
                    }
                }

                MovieListScreen(
                    movies = movies,
                    onMovieClick = { movie ->
                        Log.d("MainScreen", "Movie clicked: ${movie.title}")
                        navController.navigate(Screen.Details.createRoute(movie.id))
                    },
                    onToggleFavorite = {
                        Log.d("MainScreen", "Toggle favorite: ${it.title}")
                        viewModel.toggleFavorite(it)
                    }
                )
            }

            composable(Screen.Favorites.route) {
                val favoriteMovies by viewModel.favorites.collectAsState()

                val favoriteMoviesList = viewModel.favoriteMoviesToMovies(favoriteMovies)

                if (favoriteMoviesList.isEmpty()) {
                    Text("No featured movies")
                } else {
                    MovieListScreen(
                        movies = favoriteMoviesList,
                        onMovieClick = { movie ->
                            navController.navigate(Screen.Details.createRoute(movie.id))
                        },
                        onToggleFavorite = {
                            viewModel.toggleFavorite(it)
                        }
                    )
                }
            }

            composable(
                route = Screen.Details.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getInt("movieId") ?: return@composable

                LaunchedEffect(movieId) {
                    viewModel.loadMovieDetail(movieId, apiKey)
                }

                val movie by viewModel.movieDetail.collectAsState()

                if (movie == null) {
                    CircularProgressIndicator()
                } else {
                    MovieDetailScreen(
                        movie = movie!!,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}
