package com.example.tmdbclinet.domain.usecase

import com.example.tmdbclinet.data.model.tvshow.TVShow
import com.example.tmdbclinet.domain.repository.TvShowRepository

class UpdateTvShowsUseCase(private val tvShowRepository: TvShowRepository) {

    suspend fun execute(): List<TVShow>? =tvShowRepository.updateTvShows()
}