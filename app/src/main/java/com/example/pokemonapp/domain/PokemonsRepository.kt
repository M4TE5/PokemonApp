package com.example.pokemonapp.domain


interface PokemonsRepository  {
    suspend fun getPokemonList(): List<Pokemon>

    suspend fun getPokemonById(id: Int): Pokemon
}