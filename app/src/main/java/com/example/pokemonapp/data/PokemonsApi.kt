package com.example.pokemonapp.data


import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.data.entities.PokemonListEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonsApi {
    @GET("https://pokeapi.co/api/v2/pokemon/")
    suspend fun getPokemonList(): PokemonListEntity

    @GET("https://pokeapi.co/api/v2/pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonEntity
}