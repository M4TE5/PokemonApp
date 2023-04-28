package com.example.pokemonapp.data

import com.example.pokemonapp.data.api.PokemonService
import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonItemEntity

class ApiPokemonsDataSource: PokemonsDataSource{

    private val api = PokemonService.apiService
    override suspend fun getPokemonList(): List<PokemonEntity> {
        return api.getPokemonList().results
    }

    override suspend fun getPokemonById(id: Int): PokemonItemEntity {
        return api.getPokemonById(id)
    }
}