package com.example.pokemonapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PokemonDbEntity::class], version = 1)
abstract class PokemonDb: RoomDatabase() {
    abstract fun getDao(): Dao
}