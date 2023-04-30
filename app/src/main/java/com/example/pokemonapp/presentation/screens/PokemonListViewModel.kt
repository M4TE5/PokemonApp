package com.example.pokemonapp.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.GetPokemonListUseCase
import com.example.pokemonapp.domain.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase
    ) : ViewModel(){

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>>
        get() = _pokemonList

    fun loadData(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = getPokemonListUseCase.getPokemonList()
            withContext(Dispatchers.Main){
                _pokemonList.value = list
            }
        }
    }

}