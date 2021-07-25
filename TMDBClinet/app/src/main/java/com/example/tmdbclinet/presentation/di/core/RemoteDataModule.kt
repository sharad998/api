package com.example.tmdbclinet.presentation.di.core

import com.example.tmdbclinet.data.api.TMDBService
import com.example.tmdbclinet.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclinet.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclinet.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclinet.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclinet.data.repository.tvshow.datasourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule(private var apiKey:String) {

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(tmdbService: TMDBService): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(tmdbService, apiKey)
    }

    @Singleton
    @Provides
    fun provideTvRemoteDataSource(tmdbService: TMDBService): TvShowRemoteDataSource {
        return TvShowRemoteDataSourceImpl(tmdbService, apiKey)
    }

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(tmdbService: TMDBService): ArtistRemoteDataSource {
        return ArtistRemoteDataSourceImpl(tmdbService,apiKey)
    }


}
