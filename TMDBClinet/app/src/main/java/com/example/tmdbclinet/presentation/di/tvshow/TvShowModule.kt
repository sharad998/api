package com.example.tmdbclinet.presentation.di.tvshow

import com.example.tmdbclinet.domain.usecase.*
import com.example.tmdbclinet.presentation.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {

    @TvShowScope
    @Provides
    fun provideTvShowViewModuleFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ): TvShowViewModelFactory {
        return TvShowViewModelFactory(getTvShowsUseCase,updateTvShowsUseCase)
    }
}
