package com.example.tmdbclinet.data.repository.artist.datasource

import com.example.tmdbclinet.data.model.artist.ArtistList
import com.example.tmdbclinet.data.model.tvshow.TVShowList
import retrofit2.Response

interface ArtistRemoteDataSource {
    suspend fun getArtists():Response<ArtistList>
}