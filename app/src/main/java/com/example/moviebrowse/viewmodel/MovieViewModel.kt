package com.example.moviebrowse.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebrowse.data.repository.MovieRepository
import com.example.moviebrowse.data.api.Movie
import com.example.moviebrowse.data.db.FavoriteMovie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _favorites = MutableStateFlow<List<FavoriteMovie>>(emptyList())
    val favorites: StateFlow<List<FavoriteMovie>> = _favorites

    private val _movieDetail = MutableStateFlow<Movie?>(null)
    val movieDetail: StateFlow<Movie?> = _movieDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun loadPopularMovies(apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _movies.value = repository.getPopularMovies(apiKey)
            } catch (e: Exception) {
                _errorMessage.value = "Error loading popular movies: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMovieDetail(movieId: Int, apiKey: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _movieDetail.value = repository.getMovieById(movieId, apiKey)
            } catch (e: Exception) {
                _errorMessage.value = "Error loading movie details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getAllFavorites()
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val isFav = repository.isFavorite(movie.id)
            val favoriteMovie = FavoriteMovie(
                id = movie.id,
                title = movie.title,
                poster_path = movie.poster_path,
                overview = movie.overview,
                release_date = movie.release_date
            )
            if (isFav) repository.removeFavorite(favoriteMovie)
            else repository.addFavorite(favoriteMovie)

            loadFavorites()
        }
    }

    suspend fun isFavorite(id: Int): Boolean = repository.isFavorite(id)

    fun favoriteMoviesToMovies(favoriteMovies: List<FavoriteMovie>): List<Movie> {
        return favoriteMovies.map { favoriteMovie ->
            Movie(
                id = favoriteMovie.id,
                title = favoriteMovie.title,
                poster_path = favoriteMovie.poster_path,
                overview = favoriteMovie.overview,
                release_date = favoriteMovie.release_date
            )
        }
    }
}

