package com.example.tmdbclinet.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbclinet.domain.usecase.GetMoviesUseCase
import com.example.tmdbclinet.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclinet.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclinet.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModelFactory(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowViewModel(getTvShowsUseCase,updateTvShowsUseCase)as T
    }
}