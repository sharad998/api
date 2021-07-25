package com.example.tmdbclinet.data.repository.artist.datasource


import com.example.tmdbclinet.data.model.artist.Artist


interface ArtistCacheDataSource {
    suspend fun getArtistsFromCache():List<Artist>
    suspend fun saveArtistsToCache(artists:List<Artist>)

}