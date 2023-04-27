package com.example.pokemonapp.data

import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonItemEntity

interface PokemonsDataSource {
    suspend fun getPokemonList(): List<PokemonEntity>
    suspend fun getPokemonById(): PokemonItemEntity
}