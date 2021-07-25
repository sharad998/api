package com.example.tmdbclinet.data.api


import com.example.tmdbclinet.data.model.artist.ArtistList
import com.example.tmdbclinet.data.model.tvshow.TVShowList
import com.example.tmdbclinet.data.model.movie.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TMDBService {

    @GET("/movie/popular")
    suspend fun getPopularMovies(
        @Query(
            "api_key"
        )apiKey:String
    ):Response<MovieList>


    @GET("/tv/popular")
    suspend fun getPopularTVShows(
        @Query(
            "api_key"
        )apiKey:String
    ):Response<TVShowList>

    @GET("/person/popular")
    suspend fun getPopularArtists(
        @Query(
            "api_key"
        )apiKey:String
    ):Response<ArtistList>

}