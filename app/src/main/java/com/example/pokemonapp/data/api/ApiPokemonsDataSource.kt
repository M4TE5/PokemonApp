package com.example.pokemonapp.data.api

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.pokemonapp.data.PokemonsDataSource
import com.example.pokemonapp.domain.Pokemon
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class ApiPokemonsDataSource(private val context: Context): PokemonsDataSource {

    private val api = PokemonService.apiService
    override suspend fun getPokemonList(): List<Pokemon> {
        return api.getPokemonList().results.map { getPokemonById(it.id) }
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        return api.getPokemonById(id).toPokemon()
    }

}