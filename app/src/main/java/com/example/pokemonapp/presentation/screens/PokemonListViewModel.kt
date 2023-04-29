package com.example.pokemonapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.GetPokemonListUseCase
import com.example.pokemonapp.domain.Pokemon
import com.example.pokemonapp.domain.PokemonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonListViewModel(private val pokemonsRepository: PokemonsRepository) : ViewModel(){

    private val getPokemonListUseCase = GetPokemonListUseCase(pokemonsRepository)

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val list = getPokemonListUseCase.getPokemonList()
            withContext(Dispatchers.Main){
                _pokemonList.value = list
            }
        }
    }

}