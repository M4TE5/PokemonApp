package com.example.pokemonapp.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Dependencies
import com.example.pokemonapp.databinding.FragmentMainPokemonListBinding
import com.example.pokemonapp.presentation.PokemonListAdapter

class PokemonListFragment : Fragment() {

    private val viewModel by lazy { PokemonListViewModel(Dependencies.pokemonsRepository) }
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
        setupClickListeners()
    }

    private fun setupRecyclerView(){
        adapter = PokemonListAdapter()
        binding.apply {
            rvMainPokemonList.adapter = adapter
            viewModel.pokemonList.observe(viewLifecycleOwner){
                adapter.submitList(it)
            }
        }
    }

    private fun setupClickListeners(){
        adapter.onPokemonItemClickListener = {
            val id = it.id
            val bundle = bundleOf("pokemonId" to id)
            findNavController().navigate(R.id.action_pokemonListFragment_to_pokemonDetailsFragment, bundle)
        }
    }
}