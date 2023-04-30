package com.example.pokemonapp.data.entities


import com.example.pokemonapp.domain.Pokemon
import com.google.gson.annotations.SerializedName
import kotlin.math.roundToInt

data class PokemonItemEntity (
    val id: Int,
    val name: String,
    val types: List<Types>,
    val weight: Int,
    val height: Int,
    val sprites: Sprites
){
    fun toPokemon(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            types = types.map { it.getTypeName() },
            weight = wightInKg(),
            height = heightInCm(),
            imageUri = sprites.other.official_artwork.front_default,
            iconUri = sprites.front_default
        )
    }

    private fun wightInKg(): Int = (weight.toDouble() / 10).roundToInt()
    private fun heightInCm(): Int = height * 10

    data class Types (
        val type: Type,
    ) {
        fun getTypeName(): String{
            return type.name.replaceFirstChar { it.uppercase() }
        }

        data class Type(
            val name: String
        )
    }

    data class Sprites(
        val other: Images,
        val front_default: String
    ){
        data class Images(
            @SerializedName("official-artwork") val official_artwork: Arts
        )
        data class Arts(
            val front_default: String
        )

    }
}