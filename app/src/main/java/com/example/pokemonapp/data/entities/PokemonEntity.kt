package com.example.pokemonapp.data.entities

data class PokemonEntity (
    val name: String,
    val url: String,
){
    val id: Int
    get() = url.dropLast(1).substringAfterLast('/').toInt()
}


