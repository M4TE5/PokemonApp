package com.example.pokemonapp.domain

import com.example.pokemonapp.data.entities.PokemonEntity

class GetPokemonListUseCase(private val pokemonsRepository: PokemonsRepository) {

    suspend fun getPokemonList(): List<PokemonEntity>{
        return pokemonsRepository.getPokemonList()
    }
}