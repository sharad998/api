package com.example.tmdbclinet.data.model.movie

import com.example.tmdbclinet.data.model.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieList(

    @SerializedName("results")
    val movies: List<Movie>

)