package com.example.pokemonapp.data.api

import com.example.pokemonapp.data.api.UrlConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object PokemonService {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val apiService = retrofit.create(PokemonApi::class.java)
}