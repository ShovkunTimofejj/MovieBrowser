package com.example.moviebrowse.data.db

import androidx.room.*

@Dao
interface FavoriteMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: FavoriteMovie)

    @Delete
    suspend fun delete(movie: FavoriteMovie)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteMovie>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun getFavoriteById(id: Int): FavoriteMovie?
}
