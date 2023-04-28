package com.example.pokemonapp.data

import android.content.Context
import android.net.ConnectivityManager

object Dependencies {
    private lateinit var applicationContext: Context
    private lateinit var connectivityManager: ConnectivityManager

    fun init(context: Context){
        applicationContext = context
    }

    val pokemonsRepository: PokemonsRepositoryImpl by lazy { PokemonsRepositoryImpl(
        apiDataSource = ApiPokemonsDataSource(),
        dbDataSource = DbPokemonsDataSource()
    ) }
}