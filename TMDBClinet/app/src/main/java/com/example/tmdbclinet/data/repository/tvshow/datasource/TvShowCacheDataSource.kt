package com.example.tmdbclinet.data.repository.tvshow.datasource


import com.example.tmdbclinet.data.model.tvshow.TVShow

interface TvShowCacheDataSource {
    suspend fun getTvShowsFromCache():List<TVShow>
    suspend fun saveTvShowsToCache(tvShows:List<TVShow>)

}