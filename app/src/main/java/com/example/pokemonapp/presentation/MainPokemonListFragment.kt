package com.example.pokemonapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentMainPokemonListBinding

class MainPokemonListFragment : Fragment() {

    private val viewModel by lazy {MainPokemonListViewModel()}
    private lateinit var binding: FragmentMainPokemonListBinding
    private lateinit var adapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return layoutInflater.inflate(R.layout.fragment_main_pokemon_list,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainPokemonListBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        adapter = PokemonListAdapter()
        binding.apply {
            rvMainPokemonList.adapter = adapter
            adapter.pokemonList = listOf("Pikachu", "Pidgey")
        }
    }

}