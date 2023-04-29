package com.example.pokemonapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface Dao {

    @Query("SELECT * FROM pokemons")
    fun getAll(): List<PokemonDbEntity>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getPokemonById(id: Int): PokemonDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<PokemonDbEntity>)

    @Query("DELETE FROM pokemons")
    suspend fun deleteAll()

}