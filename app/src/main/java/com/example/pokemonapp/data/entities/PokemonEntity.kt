package com.example.pokemonapp.data.entities

data class PokemonEntity (
    val name: String,
    val url: String,
){
    val nameCap: String
        get() = name.replaceFirstChar { it.uppercase() }
    fun getId(): Int = url.dropLast(1).substringAfterLast('/').toInt()
}


