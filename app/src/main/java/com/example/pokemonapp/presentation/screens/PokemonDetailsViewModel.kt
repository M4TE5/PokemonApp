package com.example.pokemonapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.entities.PokemonItemEntity
import com.example.pokemonapp.domain.GetPokemonByIdUseCase
import com.example.pokemonapp.domain.PokemonsRepository
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(private val pokemonsRepository: PokemonsRepository) : ViewModel() {

    private val getPokemonByIdUseCase = GetPokemonByIdUseCase(pokemonsRepository)

    private val _pokemonItem = MutableLiveData<PokemonItemEntity>()
    val pokemonItem: LiveData<PokemonItemEntity>
        get() = _pokemonItem


    fun getPokemonById(id: Int){
        viewModelScope.launch{
            val pokemonItem = getPokemonByIdUseCase.getPokemonById(id)
            _pokemonItem.value = pokemonItem
        }
    }
}