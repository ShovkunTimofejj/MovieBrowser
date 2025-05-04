package com.example.moviebrowse.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{movieId}") {
        fun createRoute(movieId: Int) = "details/$movieId"
    }
    object Favorites : Screen("favorites")
}
