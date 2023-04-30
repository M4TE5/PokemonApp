package com.example.pokemonapp.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.FragmentMainPokemonListBinding
import com.example.pokemonapp.presentation.PokemonListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels()
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
        loadData()
    }
    private fun loadData(){
        viewModel.loadData()
        viewModel.pokemonList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            binding.progressBar.visibility = View.GONE
            if (it.isEmpty()){
                binding.buttonRefresh.visibility = View.VISIBLE
            } else{
                binding.buttonRefresh.visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerView(){
        adapter = PokemonListAdapter()
        binding.rvMainPokemonList.adapter = adapter
    }

    private fun setupClickListeners(){
        adapter.onPokemonItemClickListener = {
            val id = it.id
            val bundle = bundleOf("pokemonId" to id)
            findNavController().navigate(R.id.action_pokemonListFragment_to_pokemonDetailsFragment, bundle)
        }

        binding.buttonRefresh.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            loadData()
            Toast.makeText(requireContext(),"Refreshing...",Toast.LENGTH_SHORT).show()
        }
    }
}