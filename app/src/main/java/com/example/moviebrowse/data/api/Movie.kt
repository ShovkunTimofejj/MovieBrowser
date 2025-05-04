package com.example.moviebrowse.data.api

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String?,
    val overview: String,
    val release_date: String,
    val isFavorite: Boolean = false
)