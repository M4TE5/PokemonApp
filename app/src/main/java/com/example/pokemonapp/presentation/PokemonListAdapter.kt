package com.example.pokemonapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.entities.PokemonEntity
import com.example.pokemonapp.databinding.PokemonItemBinding
import okhttp3.internal.notify

class PokemonListAdapter: ListAdapter<PokemonEntity, PokemonListAdapter.PokemonListHolder>(PokemonDiffCallBack()) {
    class PokemonListHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = PokemonItemBinding.bind(view)
    }

    var onPokemonItemClickListener: ((PokemonEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonListHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        val pokemon = getItem(position)
        holder.binding.apply {
            tvName.text = pokemon.name

            pokemonCard.setOnClickListener {
                onPokemonItemClickListener?.invoke(pokemon)
            }
        }
    }

}