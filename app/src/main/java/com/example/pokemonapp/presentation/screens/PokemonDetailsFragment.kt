package com.example.pokemonapp.presentation.screens

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonapp.R
import com.example.pokemonapp.data.Dependencies
import com.example.pokemonapp.databinding.FragmentPokemonDetailsBinding

class PokemonDetailsFragment : Fragment() {
    private val viewModel by lazy { PokemonDetailsViewModel(Dependencies.pokemonsRepository) }
    private lateinit var binding: FragmentPokemonDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fillInfo()
        return layoutInflater.inflate(R.layout.fragment_pokemon_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPokemonDetailsBinding.bind(view)
    }

    private fun fillInfo() {
        viewModel.getPokemonById(getPokemonId())
        viewModel.pokemonItem.observe(viewLifecycleOwner) { pokemon ->
            binding.apply {
                tvName.text = pokemon.name
                tvHeightValue.text = pokemon.height.toString()
                tvWeightValue.text = pokemon.weight.toString()
                tvTypesValue.text = pokemon.types.joinToString()

                setupImage(pokemon.imageUri)

                progressBar.visibility = View.GONE
            }
        }
    }


    private fun setupImage(uri: String) {
        val listener = resourceListener()
        Glide.with(requireContext())
            .load(uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .listener(listener)
            .into(binding.imageView)
    }

    private fun resourceListener(): RequestListener<Drawable>{
        return object : RequestListener<Drawable>{
             override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                val drawable = resource as BitmapDrawable
                val bitmap = drawable.bitmap
                Palette.Builder(bitmap).generate() {
                    it?.let { palette ->
                        if(isAdded){
                            val dominantColor = palette.getDominantColor(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.white
                                )
                            )
                            setTextColor(getTextColor(dominantColor))
                            setNameTextColor(dominantColor)
                            changeCardBg(dominantColor)
                        }
                    }
                }
                return false
            }
        }
    }

    private fun setNameTextColor(color: Int){
        val colorBrightness = ColorUtils.calculateLuminance(color)
        val newColor = if (colorBrightness >= 0.6){
            ColorUtils.blendARGB(color, Color.BLACK, 0.3f)
        } else color
        binding.tvName.setTextColor(newColor)
    }

    private fun getTextColor(color: Int): Int{
        val colorBrightness = ColorUtils.calculateLuminance(color)
        return if(colorBrightness >= 0.5){
            Color.BLACK
        } else {
            Color.WHITE
        }
    }

    private fun changeCardBg(color: Int){
        val colorFrom = binding.statsCard.cardBackgroundColor.defaultColor
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, color)
        colorAnimation.duration = 600
        colorAnimation.addUpdateListener {
            val color = it.animatedValue as Int
            binding.statsCard.backgroundTintList = ColorStateList.valueOf(color)
        }
        colorAnimation.start()
    }

    private fun setTextColor(color: Int){
        val colorFrom = binding.tvHeight.currentTextColor
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, color)
        colorAnimation.duration = 600
        binding.apply {
            tvTypes.setTextColor(color)
            tvTypesValue.setTextColor(color)
            tvHeight.setTextColor(color)
            tvHeightValue.setTextColor(color)
            tvWeight.setTextColor(color)
            tvWeightValue.setTextColor(color)
        }
    }

    private fun getPokemonId(): Int = requireArguments().getInt(POKEMON_ID_KEY)

    companion object{
        const val POKEMON_ID_KEY = "pokemonId"
    }
}