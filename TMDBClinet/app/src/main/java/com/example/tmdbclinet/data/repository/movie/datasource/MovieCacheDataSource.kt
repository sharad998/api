package com.example.tmdbclinet.data.repository.movie.datasource

import com.example.tmdbclinet.data.model.movie.Movie

interface MovieCacheDataSource {
    suspend fun getMoviesFromCache():List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)

}