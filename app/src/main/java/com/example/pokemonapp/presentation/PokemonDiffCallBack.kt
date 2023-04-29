package com.example.pokemonapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.pokemonapp.domain.Pokemon

class PokemonDiffCallBack: DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}