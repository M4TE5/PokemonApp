package com.example.pokemonapp.data

import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonItemEntity

class ApiPokemonsDataSource(): PokemonsDataSource{
    override suspend fun getPokemonList(): List<PokemonEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonById(): PokemonItemEntity {
        TODO("Not yet implemented")
    }
}