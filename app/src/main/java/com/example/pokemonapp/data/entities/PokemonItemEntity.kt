package com.example.pokemonapp.data.entities

data class PokemonItemEntity (
    val name: String,
    val types: List<Type>,
    val weight: Int,
    val height: Int
)