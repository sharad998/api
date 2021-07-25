package com.example.tmdbclinet.domain.repository

import com.example.tmdbclinet.data.model.artist.Artist

interface ArtistRepository {

    suspend fun getArtists():List<Artist>?
    suspend fun updateArtists():List<Artist>?
}