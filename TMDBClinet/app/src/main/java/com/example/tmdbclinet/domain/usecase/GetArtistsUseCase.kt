package com.example.tmdbclinet.domain.usecase

import com.example.tmdbclinet.data.model.artist.Artist
import com.example.tmdbclinet.domain.repository.ArtistRepository

class GetArtistsUseCase(private val artistRepository: ArtistRepository) {
    suspend fun execute():List<Artist>? = artistRepository.getArtists()
}