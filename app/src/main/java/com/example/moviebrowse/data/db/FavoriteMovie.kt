package com.example.moviebrowse.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteMovie(
    @PrimaryKey val id: Int,
    val title: String,
    val poster_path: String?,
    val overview: String,
    val release_date: String
)