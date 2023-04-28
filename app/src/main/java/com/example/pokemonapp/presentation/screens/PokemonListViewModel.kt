package com.example.pokemonapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.domain.GetPokemonListUseCase
import com.example.pokemonapp.domain.PokemonsRepository
import kotlinx.coroutines.launch

class PokemonListViewModel(private val pokemonsRepository: PokemonsRepository) : ViewModel(){

    private val getPokemonListUseCase = GetPokemonListUseCase(pokemonsRepository)

    private val _pokemonList = MutableLiveData<List<PokemonEntity>>()
    val pokemonList: LiveData<List<PokemonEntity>>
        get() = _pokemonList

    init {
        viewModelScope.launch {
            val list = getPokemonListUseCase.getPokemonList()
            _pokemonList.value = list
        }
    }

}