package com.example.pokemonapp.domain


class GetPokemonByIdUseCase(private val pokemonsRepository: PokemonsRepository) {

    suspend fun getPokemonById(id: Int): Pokemon {
        return pokemonsRepository.getPokemonById(id)
    }

}