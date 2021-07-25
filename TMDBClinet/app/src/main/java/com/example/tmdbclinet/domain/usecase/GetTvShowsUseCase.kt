package com.example.tmdbclinet.domain.usecase

import com.example.tmdbclinet.data.model.tvshow.TVShow
import com.example.tmdbclinet.domain.repository.TvShowRepository

//nio class or interface or abstract class can be directly assigned or used unless its object is created

class GetTvShowsUseCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TVShow>? =tvShowRepository.getTvShows()
}