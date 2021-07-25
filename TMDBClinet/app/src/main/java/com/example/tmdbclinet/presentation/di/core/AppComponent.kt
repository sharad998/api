package com.example.tmdbclinet.presentation.di.core

import com.example.tmdbclinet.presentation.di.artist.ArtistSubComponent
import com.example.tmdbclinet.presentation.di.movie.MovieSubComponent
import com.example.tmdbclinet.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetModule::class,
    DataBaseModule::class,
    RepositoryModule::class,
    RemoteDataModule::class,
    LocalDataModule::class,
    CacheDataModule::class,
    UseCaseModule::class
])
interface AppComponent{
    fun movieSubComponent():MovieSubComponent.Factory
    fun tvShowSubComponent():TvShowSubComponent.Factory
    fun artistSubComponent():ArtistSubComponent.Factory

}