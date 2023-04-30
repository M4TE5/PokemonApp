package com.example.pokemonapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonDbEntity::class], version = 1)
abstract class PokemonDb: RoomDatabase() {
    abstract fun getDao(): Dao

    companion object{
        fun getInstance(context: Context): PokemonDb{
            return Room.databaseBuilder(context, PokemonDb::class.java, "pokemons.db").build()
        }
    }
}