package com.example.pokemonapp.domain

import com.example.pokemonapp.data.entities.PokemonItemEntity


class GetPokemonByIdUseCase(private val pokemonsRepository: PokemonsRepository) {

    suspend fun getPokemonById(id: Int): PokemonItemEntity {
        return pokemonsRepository.getPokemonById(id)
    }

}