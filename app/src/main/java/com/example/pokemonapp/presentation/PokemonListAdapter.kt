package com.example.pokemonapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.PokemonItemBinding

class PokemonListAdapter: RecyclerView.Adapter<PokemonListAdapter.PokemonListHolder>() {
    class PokemonListHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = PokemonItemBinding.bind(view)
    }

    var pokemonList = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonListHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        val pokemonName = pokemonList[position]
        holder.binding.apply {
            tvName.text = pokemonName
        }
    }

    override fun getItemCount(): Int = pokemonList.size
}