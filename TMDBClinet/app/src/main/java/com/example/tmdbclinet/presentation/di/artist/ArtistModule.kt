package com.example.tmdbclinet.presentation.di.artist

import com.example.tmdbclinet.domain.usecase.GetArtistsUseCase
import com.example.tmdbclinet.domain.usecase.UpdateArtistsUseCase
import com.example.tmdbclinet.presentation.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun provideArtistViewModuleFactory(
        getArtistsUseCase: GetArtistsUseCase,
        updateArtistsUseCase: UpdateArtistsUseCase
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(getArtistsUseCase,updateArtistsUseCase)
    }
}
