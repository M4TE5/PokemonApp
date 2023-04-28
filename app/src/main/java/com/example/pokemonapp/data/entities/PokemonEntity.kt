package com.example.pokemonapp.data.entities

data class PokemonEntity (
    val name: String,
    val url: String,
){
    fun getId(): Int = url.dropLast(1).substringAfterLast('/').toInt()
}
