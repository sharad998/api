package com.example.tmdbclinet.data.repository.movie.datasource

import com.example.tmdbclinet.data.model.movie.Movie
import com.example.tmdbclinet.data.model.movie.MovieList
import retrofit2.Response

interface MovieRemoteDataSource {
    suspend fun getMovies():Response<MovieList>
}