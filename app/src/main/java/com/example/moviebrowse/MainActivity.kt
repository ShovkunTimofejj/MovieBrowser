package com.example.moviebrowse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moviebrowse.data.api.RetrofitInstance
import com.example.moviebrowse.data.db.MovieDatabase
import com.example.moviebrowse.data.repository.MovieRepository
import com.example.moviebrowse.ui.theme.MovieBrowseTheme
import com.example.moviebrowse.viewmodel.MovieViewModel
import com.example.moviebrowse.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = MovieDatabase.getDatabase(applicationContext)

        val repository = MovieRepository(
            api = RetrofitInstance.api,
            favoriteDao = database.favoriteMovieDao()
        )

        setContent {
            MovieBrowseTheme {
                val viewModel: MovieViewModel = viewModel(
                    factory = MovieViewModelFactory(repository)
                )

                MainScreen(
                    viewModel = viewModel,
                    apiKey = BuildConfig.API_KEY
                )
            }
        }
    }
}


