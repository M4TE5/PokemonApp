package com.example.pokemonapp.data

import android.content.Context
import androidx.room.Room
import com.example.pokemonapp.data.api.ApiPokemonsDataSource
import com.example.pokemonapp.data.db.DbPokemonsDataSource
import com.example.pokemonapp.data.db.PokemonDb

object Dependencies {
    private lateinit var applicationContext: Context

    fun init(context: Context) {
        applicationContext = context
    }

    val pokemonsRepository: PokemonsRepositoryImpl by lazy {
        PokemonsRepositoryImpl(
            apiDataSource = ApiPokemonsDataSource(applicationContext),
            dbDataSource = DbPokemonsDataSource(database.getDao(), ImageUtils(applicationContext) ),
            networkUtils = NetworkUtils(applicationContext)
        )
    }

    private val database: PokemonDb by lazy {
        Room.databaseBuilder(applicationContext, PokemonDb::class.java, "pokemons.db").build()
    }
}