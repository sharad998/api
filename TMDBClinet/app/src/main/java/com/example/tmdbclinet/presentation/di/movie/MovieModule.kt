package com.example.tmdbclinet.presentation.di.movie

import com.example.tmdbclinet.domain.usecase.GetMoviesUseCase
import com.example.tmdbclinet.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclinet.presentation.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun provideMovieViewModuleFactory(
        getMoviesUseCase: GetMoviesUseCase,
        updateMoviesUseCase: UpdateMoviesUseCase
    ): MovieViewModelFactory {
        return MovieViewModelFactory(getMoviesUseCase,updateMoviesUseCase)
    }
}
