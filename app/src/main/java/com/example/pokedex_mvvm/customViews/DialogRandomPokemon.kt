package com.example.pokedex_mvvm.customViews

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.extensions.loadImageWithShimmer
import com.example.pokedex_mvvm.ui.main.pokemoninfo.adapter.PokemonTypeAdapter
import com.facebook.shimmer.ShimmerFrameLayout

class DialogRandomPokemon(private val pokemonRandom: PokemonDetailsDomain) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_dialog_pokemon_random, null)
        requireActivity().window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val closeButton = dialogView.findViewById<ImageView>(R.id.close_button)
        val imagePokemon = dialogView.findViewById<ImageView>(R.id.image_pokemon)
        val namePokemon = dialogView.findViewById<TextView>(R.id.pokemon_name)
        val weighPokemon = dialogView.findViewById<TextView>(R.id.txt_weight)
        val heightPokemon = dialogView.findViewById<TextView>(R.id.txt_height)
        val rvTypePokemon = dialogView.findViewById<RecyclerView>(R.id.rv_type_pokemon)

        rvTypePokemon.apply {
            adapter = PokemonTypeAdapter(pokemonRandom.types)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        val shimmer = dialogView.findViewById<ShimmerFrameLayout>(R.id.shimmer)

        imagePokemon.loadImageWithShimmer(pokemonRandom.sprites.front_default, shimmer)

        "${pokemonRandom.name} ${requireActivity().getString(R.string.escolho_voce)}".also {
            namePokemon.text = it
        }
        "${pokemonRandom.weight} ${requireActivity().getString(R.string.kg)}".also {
            weighPokemon.text = it
        }
        "${pokemonRandom.height} ${requireActivity().getString(R.string.metros)}".also {
            heightPokemon.text = it
        }

        closeButton.setOnClickListener { dismiss() }
        builder.setView(dialogView)

        return builder.create()
    }

    companion object {
        const val TAG = "CUSTOM_POKEMON_RANDOM_DIALOG"
    }

}