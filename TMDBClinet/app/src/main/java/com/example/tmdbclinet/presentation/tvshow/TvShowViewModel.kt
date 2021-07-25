package com.example.tmdbclinet.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclinet.domain.usecase.GetMoviesUseCase
import com.example.tmdbclinet.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclinet.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclinet.domain.usecase.UpdateTvShowsUseCase

class TvShowViewModel(
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
): ViewModel() {

    fun getTvShows()=liveData{
        val tvShowList=getTvShowsUseCase.execute()
        emit(tvShowList)
    }

    fun updateTvShows()= liveData{
        val tvShowList=updateTvShowsUseCase.execute()
        emit(tvShowList)
    }
}