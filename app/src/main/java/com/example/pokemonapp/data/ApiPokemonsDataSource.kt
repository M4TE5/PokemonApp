package com.example.pokemonapp.data

import android.util.Log
import com.example.pokemonapp.data.api.PokemonService
import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonItemEntity

class ApiPokemonsDataSource(): PokemonsDataSource{
    override suspend fun getPokemonList(): List<PokemonEntity> {
        val api = PokemonService.apiService
        val list =  api.getPokemonList()
        return list.results
    }

    override suspend fun getPokemonById(): PokemonItemEntity {
        TODO("Not yet implemented")
    }
}