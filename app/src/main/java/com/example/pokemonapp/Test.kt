package com.example.pokemonapp

import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class PokemonListResponseBody(
    val results: List<PokemonName>
)

data class PokemonName(
    val name: String,
)

data class Pokemon(
    val name: String,
    val types: List<Type>,
    val weight: Int,
    val height: Int
)

data class Type(
    val name: String
)

interface Api{

    @GET("https://pokeapi.co/api/v2/pokemon/")
    suspend fun getPokemonList(): PokemonListResponseBody

    @GET("")
    suspend fun getPokemonById(@Path("id") id: Int): Pokemon
}

fun main() = runBlocking{

    val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(Api::class.java)

    val list = api.getPokemonList()
    val pokemon = api.getPokemonById(1)

    println(pokemon.name)
}