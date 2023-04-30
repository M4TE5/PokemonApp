package com.example.pokemonapp.data

import com.example.pokemonapp.data.api.ApiPokemonsDataSource
import com.example.pokemonapp.data.db.DbPokemonsDataSource
import com.example.pokemonapp.data.utils.NetworkUtils
import com.example.pokemonapp.domain.Pokemon
import com.example.pokemonapp.domain.PokemonsRepository

class PokemonsRepositoryImpl(
    private val apiDataSource: ApiPokemonsDataSource,
    private val dbDataSource: DbPokemonsDataSource,
    private val networkUtils: NetworkUtils
) : PokemonsRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        var dataSource = getDataSource()
        return try {
            val pokemonList = dataSource.getPokemonList()
            if (dataSource == apiDataSource) {
                dbDataSource.deleteData()
                dbDataSource.insertData(pokemonList)
            }
            pokemonList
        } catch (e: Exception) {
            dataSource = dbDataSource
            dataSource.getPokemonList()
        }
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        var dataSource = getDataSource()
        return try {
            dataSource.getPokemonById(id)
        } catch (e: java.lang.Exception) {
            dataSource = dbDataSource
            dataSource.getPokemonById(id)
        }
    }

    private fun getDataSource(): PokemonsDataSource {
        return if (networkUtils.isNetworkAvailable()) {
            apiDataSource
        } else {
            dbDataSource
        }
    }
}