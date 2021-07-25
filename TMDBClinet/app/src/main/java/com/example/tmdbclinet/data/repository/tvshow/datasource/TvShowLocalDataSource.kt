package com.example.tmdbclinet.data.repository.tvshow.datasource


import com.example.tmdbclinet.data.model.tvshow.TVShow

interface TvShowLocalDataSource {
    suspend fun getTvShowsFromDB():List<TVShow>
    suspend fun saveTvShowsToDB(tvShows:List<TVShow>)
    suspend fun clearAll()
}