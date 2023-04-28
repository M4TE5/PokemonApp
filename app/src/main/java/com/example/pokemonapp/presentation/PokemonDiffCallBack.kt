package com.example.pokemonapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapp.data.entities.PokemonEntity

class PokemonDiffCallBack: DiffUtil.ItemCallback<PokemonEntity>() {
    override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
        return oldItem == newItem
    }
}