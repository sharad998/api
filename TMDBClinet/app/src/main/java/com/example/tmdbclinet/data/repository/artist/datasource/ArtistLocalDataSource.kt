package com.example.tmdbclinet.data.repository.artist.datasource


import com.example.tmdbclinet.data.model.artist.Artist


interface ArtistLocalDataSource {
    suspend fun getArtistsFromDB():List<Artist>
    suspend fun saveArtistsToDB(artists:List<Artist>)
    suspend fun clearAll()
}