package com.example.tmdbclinet.presentation.di.core

import com.example.tmdbclinet.data.db.ArtistDao
import com.example.tmdbclinet.data.db.MovieDao
import com.example.tmdbclinet.data.db.TvShowDao
import com.example.tmdbclinet.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclinet.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclinet.data.repository.artist.datasourceImpl.ArtistLocalDataSourceImpl
import com.example.tmdbclinet.data.repository.artist.datasourceImpl.ArtistRemoteDataSourceImpl
import com.example.tmdbclinet.data.repository.movie.datasource.MovieLocalDataSource
import com.example.tmdbclinet.data.repository.movie.datasource.MovieRemoteDataSource
import com.example.tmdbclinet.data.repository.movie.datasourceImpl.MovieLocalDataSourceImpl
import com.example.tmdbclinet.data.repository.movie.datasourceImpl.MovieRemoteDataSourceImpl
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowLocalDataSource
import com.example.tmdbclinet.data.repository.tvshow.datasource.TvShowRemoteDataSource
import com.example.tmdbclinet.data.repository.tvshow.datasourceImpl.TvShowLocalDataSourceImpl
import com.example.tmdbclinet.data.repository.tvshow.datasourceImpl.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {
        @Singleton
        @Provides
        fun provideMovieRemoteDataSource(movieDao: MovieDao): MovieLocalDataSource {
            return MovieLocalDataSourceImpl(movieDao)
        }


        @Singleton
        @Provides
        fun provideTvLocalDataSource(tvShowDao: TvShowDao): TvShowLocalDataSource {
            return TvShowLocalDataSourceImpl(tvShowDao)
        }

        @Singleton
        @Provides
        fun provideArtistLocalDataSource(artistDao: ArtistDao): ArtistLocalDataSource {
            return ArtistLocalDataSourceImpl(artistDao)
        }



}