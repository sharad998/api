package com.example.tmdbclinet.data.repository.tvshow.datasourceImpl

import com.example.tmdbclinet.data.api.TMDBService
import com.example.tmdbclinet.data.model.tvshow.TVShowList
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowRemoteDataSource
import retrofit2.Response

class TvShowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String
): TvShowRemoteDataSource {
    override suspend fun getTvShows(): Response<TVShowList> =tmdbService.getPopularTVShows(apiKey)
}