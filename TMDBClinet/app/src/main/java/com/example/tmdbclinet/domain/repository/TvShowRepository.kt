package com.example.tmdbclinet.domain.repository

import com.example.tmdbclinet.data.model.tvshow.TVShow

interface TvShowRepository {
    suspend fun getTvShows():List<TVShow>?
    suspend fun updateTvShows():List<TVShow>?
}