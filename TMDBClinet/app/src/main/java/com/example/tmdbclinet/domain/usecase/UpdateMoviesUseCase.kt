package com.example.tmdbclinet.domain.usecase

import com.example.tmdbclinet.data.model.movie.Movie
import com.example.tmdbclinet.domain.repository.MovieRepository

class UpdateMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? =movieRepository.updateMovies()
}