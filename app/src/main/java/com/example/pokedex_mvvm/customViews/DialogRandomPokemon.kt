package com.example.pokedex_mvvm.customViews

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain
import com.facebook.shimmer.ShimmerFrameLayout

class DialogRandomPokemon(private val pokemonRandom: PokemonInfoDomain) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_pokemon_random, null)
        requireActivity().window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        val closeButton = dialogView.findViewById<ImageView>(R.id.close_button)
        val imagePokemon = dialogView.findViewById<ImageView>(R.id.image_pokemon)
        val namePokemon = dialogView.findViewById<TextView>(R.id.pokemon_name)
        val shimmer = dialogView.findViewById<ShimmerFrameLayout>(R.id.shimmer)

        loadImageWithShimmer(imagePokemon, pokemonRandom.imageUrl(), shimmer)
        namePokemon.text = pokemonRandom.name
        closeButton.setOnClickListener { dismiss() }
        builder.setView(dialogView)

        return builder.create()
    }

    private fun loadImageWithShimmer(
        imageView: ImageView,
        imageUrl: String,
        shimmer: ShimmerFrameLayout
    ) {
        val requestBuilder = Glide.with(imageView.context)
            .load(imageUrl)
            .placeholder(R.drawable.placeholder_img)
            .error(R.drawable.error_img)
            .centerCrop()
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    shimmer.hideShimmer()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    shimmer.hideShimmer()
                    return false
                }
            })

        shimmer.startShimmer()

        requestBuilder.into(imageView)
    }


    companion object{
        const val TAG = "CUSTOM_POKEMON_RANDOM_DIALOG"
    }

}