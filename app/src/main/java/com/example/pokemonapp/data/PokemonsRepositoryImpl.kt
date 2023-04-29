package com.example.pokemonapp.data

import android.util.Log
import com.example.pokemonapp.data.api.ApiPokemonsDataSource
import com.example.pokemonapp.data.db.DbPokemonsDataSource
import com.example.pokemonapp.domain.Pokemon
import com.example.pokemonapp.domain.PokemonsRepository

class PokemonsRepositoryImpl(
    private val apiDataSource: ApiPokemonsDataSource,
    private val dbDataSource: DbPokemonsDataSource,
    private val networkUtils: NetworkUtils
): PokemonsRepository {

    override suspend fun getPokemonList(): List<Pokemon>{
        val dataSource = getDataSource()
        Log.d("LLL",dataSource.toString())
            val pokemonList = dataSource.getPokemonList()

            if (dataSource == apiDataSource){
                dbDataSource.deleteData()
                dbDataSource.insertData(pokemonList)
            }
            return pokemonList

    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        val dataSource = getDataSource()
        return dataSource.getPokemonById(id)
    }

    private fun getDataSource(): PokemonsDataSource{
        return if (networkUtils.isNetworkAvailable()){
            apiDataSource
        } else {
            dbDataSource
        }
    }
}