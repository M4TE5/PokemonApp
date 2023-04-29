package com.example.pokemonapp.data

import com.example.pokemonapp.domain.Pokemon

interface PokemonsDataSource {
    suspend fun getPokemonList(): List<Pokemon>
    suspend fun getPokemonById(id: Int): Pokemon
}