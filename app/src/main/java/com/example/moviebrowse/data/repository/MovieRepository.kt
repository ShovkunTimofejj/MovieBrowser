package com.example.moviebrowse.data.repository

import com.example.moviebrowse.data.api.Movie
import com.example.moviebrowse.data.api.MovieApi
import com.example.moviebrowse.data.db.FavoriteMovie
import com.example.moviebrowse.data.db.FavoriteMovieDao

class MovieRepository(
    private val api: MovieApi,
    private val favoriteDao: FavoriteMovieDao
) {
    suspend fun getPopularMovies(apiKey: String): List<Movie> {
        return api.getPopularMovies(apiKey).results
    }

    suspend fun addFavorite(movie: FavoriteMovie) = favoriteDao.insert(movie)
    suspend fun removeFavorite(movie: FavoriteMovie) = favoriteDao.delete(movie)
    suspend fun getAllFavorites(): List<FavoriteMovie> = favoriteDao.getAllFavorites()
    suspend fun isFavorite(id: Int): Boolean = favoriteDao.getFavoriteById(id) != null
    suspend fun getMovieById(movieId: Int, apiKey: String): Movie {
        return api.getMovieById(movieId, apiKey)
    }
}
