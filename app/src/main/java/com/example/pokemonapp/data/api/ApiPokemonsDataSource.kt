package com.example.pokemonapp.data.api

import com.example.pokemonapp.data.PokemonsDataSource
import com.example.pokemonapp.domain.Pokemon

class ApiPokemonsDataSource : PokemonsDataSource {

    private val api = PokemonService.apiService
    override suspend fun getPokemonList(): List<Pokemon> {
        return api.getPokemonList().results.map { getPokemonById(it.id) }
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        return api.getPokemonById(id).toPokemon()
    }

}