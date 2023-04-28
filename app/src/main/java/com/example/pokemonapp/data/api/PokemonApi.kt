package com.example.pokemonapp.data.api


import com.example.pokemonapp.data.api.UrlConstants.GET_POKEMON_ITEM_URL
import com.example.pokemonapp.data.api.UrlConstants.GET_POKEMON_LIST_URL
import com.example.pokemonapp.data.api.UrlConstants.ID_PATH
import com.example.pokemonapp.data.entities.PokemonItemEntity
import com.example.pokemonapp.data.entities.PokemonListEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {
    @GET("pokemon/")
    suspend fun getPokemonList(): PokemonListEntity

    @GET(GET_POKEMON_ITEM_URL)
    suspend fun getPokemonById(@Path(ID_PATH) id: Int): PokemonItemEntity
}