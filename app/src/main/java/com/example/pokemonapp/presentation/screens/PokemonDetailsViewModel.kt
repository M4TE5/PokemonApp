package com.example.pokemonapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.GetPokemonByIdUseCase
import com.example.pokemonapp.domain.Pokemon
import com.example.pokemonapp.domain.PokemonsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
    ) : ViewModel() {
    private val _pokemonItem = MutableLiveData<Pokemon>()
    val pokemonItem: LiveData<Pokemon>
        get() = _pokemonItem


    fun getPokemonById(id: Int){
        viewModelScope.launch (Dispatchers.IO){
            val pokemonItem = getPokemonByIdUseCase.getPokemonById(id)
            withContext(Dispatchers.Main){
                _pokemonItem.value = pokemonItem
            }
        }
    }
}