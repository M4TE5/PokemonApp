package com.example.pokemonapp.data.entities

import com.google.gson.annotations.SerializedName

data class Sprites(
    val other: Images
){
    data class Images(
        @SerializedName("official-artwork") val official_artwork: Arts
    )

    data class Arts(
        val front_default: String
    )
}