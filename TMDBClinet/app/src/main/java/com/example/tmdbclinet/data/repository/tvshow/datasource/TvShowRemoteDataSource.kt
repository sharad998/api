package com.example.tmdbclinet.data.repository.tvshow.datasource

import com.example.tmdbclinet.data.model.tvshow.TVShowList
import retrofit2.Response

interface TvShowRemoteDataSource {
    suspend fun getTvShows():Response<TVShowList>
}