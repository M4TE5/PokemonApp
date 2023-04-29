package com.example.pokemonapp.data.db

import com.example.pokemonapp.data.ImageUtils
import com.example.pokemonapp.data.PokemonsDataSource
import com.example.pokemonapp.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DbPokemonsDataSource(private val dao: Dao, private val imageUtils: ImageUtils): PokemonsDataSource {
    override suspend fun getPokemonList(): List<Pokemon> {
        return dao.getAll().map { it.toPokemon() }
    }

    override suspend fun getPokemonById(id: Int): Pokemon {
        return dao.getPokemonById(id).toPokemon()
    }

    suspend fun insertData(list: List<Pokemon>){
        withContext(Dispatchers.IO){
            val newList = list.map {
                it.copy(
                    imageUri = imageUtils.saveImageAndGetUri(it.name + IMAGE_SUFFIX,it.imageUri),
                    iconUri = imageUtils.saveImageAndGetUri(it.name + ICON_SUFFIX, it.iconUri)
                ).toDbEntity() }
            dao.insertAll(newList)
        }
    }

    suspend fun deleteData(){
        withContext(Dispatchers.IO){
            dao.deleteAll()
        }
    }
    companion object{
        const val IMAGE_SUFFIX = "_image"
        const val ICON_SUFFIX = "_icon"
    }
}