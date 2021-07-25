package com.example.tmdbclinet.presentation.di

import com.example.tmdbclinet.presentation.di.artist.ArtistSubComponent
import com.example.tmdbclinet.presentation.di.movie.MovieSubComponent
import com.example.tmdbclinet.presentation.di.tvshow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubComponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}