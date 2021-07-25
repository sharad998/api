package com.example.tmdbclinet.presentation.di.core

import com.example.tmdbclinet.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclinet.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.example.tmdbclinet.data.repository.movie.datasource.MovieCacheDataSource
import com.example.tmdbclinet.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowCacheDataSource
import com.example.tmdbclinet.data.repository.tvshow.datasourceImpl.TvShowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource():MovieCacheDataSource{
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvShowCacheDataSource(): TvShowCacheDataSource {
        return TvShowCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource(): ArtistCacheDataSource {
        return ArtistCacheDataSourceImpl()
    }
}