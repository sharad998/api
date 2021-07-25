package com.example.tmdbclinet.domain.repository

import com.example.tmdbclinet.data.model.movie.Movie

interface MovieRepository {
    suspend fun getMovies():List<Movie>?
    suspend fun updateMovies():List<Movie>?
}