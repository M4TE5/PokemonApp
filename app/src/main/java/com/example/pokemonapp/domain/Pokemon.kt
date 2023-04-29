package com.example.pokemonapp.domain

import com.example.pokemonapp.data.db.PokemonDbEntity

data class Pokemon(
     var id: Int,
     var name: String,
     var types: List<String>,
     var height: Int,
     var weight: Int,
     var imageUri: String,
     var iconUri: String
){

     fun toDbEntity(): PokemonDbEntity{
          return PokemonDbEntity(
               id = id,
               name = name,
               types = types.joinToString(),
               height = height,
               weight = weight,
               imageUri = imageUri,
               iconUri = iconUri
          )
     }

}