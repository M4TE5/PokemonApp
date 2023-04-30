package com.example.pokemonapp.di

import com.example.pokemonapp.domain.GetPokemonByIdUseCase
import com.example.pokemonapp.domain.GetPokemonListUseCase
import com.example.pokemonapp.domain.PokemonsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetPokemonListUseCase(pokemonsRepository: PokemonsRepository): GetPokemonListUseCase{
        return GetPokemonListUseCase(pokemonsRepository)
    }

    @Provides
    fun provideGetPokemonByIdUseCase(pokemonsRepository: PokemonsRepository): GetPokemonByIdUseCase{
        return GetPokemonByIdUseCase(pokemonsRepository)
    }
}
