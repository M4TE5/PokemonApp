package com.example.pokemonapp.domain

import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonItemEntity

interface PokemonsRepository  {
    suspend fun getPokemonList(): List<PokemonEntity>

    suspend fun getPokemonById(id: Int): PokemonItemEntity
}