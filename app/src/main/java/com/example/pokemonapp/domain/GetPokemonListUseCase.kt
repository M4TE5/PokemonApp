package com.example.pokemonapp.domain

class GetPokemonListUseCase(private val pokemonsRepository: PokemonsRepository) {

    suspend fun getPokemonList(): List<Pokemon>{
        return pokemonsRepository.getPokemonList()
    }
}