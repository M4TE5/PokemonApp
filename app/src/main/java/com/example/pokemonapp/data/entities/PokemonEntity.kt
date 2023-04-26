package com.example.pokemonapp.data.entities

import com.example.pokemonapp.Type

data class PokemonEntity (
    val name: String,
    val types: List<Type>,
    val weight: Int,
    val height: Int
)