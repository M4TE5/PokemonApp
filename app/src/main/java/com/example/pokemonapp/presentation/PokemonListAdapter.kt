package com.example.pokemonapp.presentation

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.PokemonItemBinding
import com.example.pokemonapp.domain.Pokemon


class PokemonListAdapter :
    ListAdapter<Pokemon, PokemonListAdapter.PokemonListHolder>(PokemonDiffCallBack()) {
    class PokemonListHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PokemonItemBinding.bind(view)
    }

    var onPokemonItemClickListener: ((Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return PokemonListHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonListHolder, position: Int) {
        val pokemon = getItem(position)
        holder.binding.apply {

            tvName.text = formatName(pokemon.name)
            tvId.text = getStringId(pokemon.id)

            setUpImage(holder, pokemon.iconUri)

            pokemonCard.setOnClickListener {
                onPokemonItemClickListener?.invoke(pokemon)
            }
        }
    }

    private fun setUpImage(holder: PokemonListHolder, uri: String) {
        val listener = resourceListener(holder)
        Glide.with(holder.itemView.context)
            .load(uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .fitCenter()
            .listener(listener)
            .into(holder.binding.pokemonIcon)
    }

    private fun resourceListener(holder: PokemonListHolder): RequestListener<Drawable> {
        return object : RequestListener<Drawable> {
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
                        val dominantColor = palette.getDominantColor(
                            ContextCompat.getColor(
                                holder.itemView.context,
                                R.color.white
                            )
                        )
                        holder.binding.pokemonCard.strokeColor = dominantColor
                        holder.binding.divider.setBackgroundColor(dominantColor)
                    }

                }
                return false
            }
        }
    }

    private fun formatName(name: String): String = name.replaceFirstChar { it.uppercase() }

    private fun getStringId(id: Int): String = ID_PREFIX + ("000$id").takeLast(3)

    companion object {
        const val ID_PREFIX = "#"
    }
}