package com.example.pokemonapp.data

import android.net.ConnectivityManager
import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonItemEntity
import com.example.pokemonapp.domain.PokemonsRepository

class PokemonsRepositoryImpl(
    private val apiDataSource: ApiPokemonsDataSource,
    private val dbDataSource: DbPokemonsDataSource,
): PokemonsRepository {

    override suspend fun getPokemonList(): List<PokemonEntity>{
//        val dataSource = if (connectivityManager.activeNetwork != null){
//            apiDataSource
//        } else {
//            dbDataSource
//        }

        val dataSource = apiDataSource

        val pokemonList = dataSource.getPokemonList()

        if(dataSource == apiDataSource){
            //TODO: Save data to db
        }
        return pokemonList
    }

    override suspend fun getPokemonById(id: Int): PokemonItemEntity {
        TODO("Not yet implemented")
    }
}