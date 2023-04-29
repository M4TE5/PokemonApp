package com.example.pokemonapp.data.db

import com.example.pokemonapp.data.PokemonsDataSource
import com.example.pokemonapp.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbPokemonsDataSource(private val dao: Dao): PokemonsDataSource {
    override suspend fun getPokemonList(): List<Pokemon> {
        return dao.getAll().map { it.toPokemon() }
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        return dao.getPokemonById(id).toPokemon()
    }

    suspend fun insertData(list: List<Pokemon>){
        withContext(Dispatchers.IO){
            dao.insertAll(list.map { it.toDbEntity() })
        }
    }

    suspend fun deleteData(){
        withContext(Dispatchers.IO){
            dao.deleteAll()
        }
    }
}