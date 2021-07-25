package com.example.tmdbclinet.data.repository.artist.datasourceImpl


import com.example.tmdbclinet.data.model.artist.Artist
import com.example.tmdbclinet.data.repository.artist.datasource.ArtistCacheDataSource


class ArtistCacheDataSourceImpl: ArtistCacheDataSource {
    private var artistList=ArrayList<Artist>()
    override suspend fun getArtistsFromCache(): List<Artist> {
      return artistList
    }

    override suspend fun saveArtistsToCache(artists: List<Artist>) {
        artistList.clear()
        artistList=ArrayList(artists)
    }
}