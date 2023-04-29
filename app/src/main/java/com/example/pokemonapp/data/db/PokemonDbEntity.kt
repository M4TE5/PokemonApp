package com.example.pokemonapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemonapp.data.ImageUtils
import com.example.pokemonapp.domain.Pokemon

@Entity(tableName = "pokemons")
data class PokemonDbEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "types") var types: String,
    @ColumnInfo(name = "height") var height: Int,
    @ColumnInfo(name = "weight") var weight: Int,
    @ColumnInfo(name = "image") var imageUri: String,
    @ColumnInfo(name = "icon") var iconUri: String
){

    fun toPokemon(): Pokemon {
        return Pokemon(
            id = id,
            name = name,
            types = types.split(", "),
            weight = weight,
            height = height,
            imageUri = imageUri,
            iconUri = iconUri
        )
    }

}
