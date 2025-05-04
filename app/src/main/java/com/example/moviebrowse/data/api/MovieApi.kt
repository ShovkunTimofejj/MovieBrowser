package com.example.moviebrowse.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("movie/{movieId}")
    suspend fun getMovieById(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String
    ): Movie

}