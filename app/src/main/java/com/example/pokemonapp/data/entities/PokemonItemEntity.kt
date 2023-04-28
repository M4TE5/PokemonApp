package com.example.pokemonapp.data.entities

data class PokemonItemEntity (
    val name: String,
    val types: List<Types>,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
){
    val nameCap: String
        get() = name.replaceFirstChar { it.uppercase() }
}