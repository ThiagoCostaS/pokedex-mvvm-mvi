package com.example.pokedex_mvvm.ui.main.listpokemons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_mvvm.databinding.PokemonItemBinding
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.extensions.loadImageWithShimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.card.MaterialCardView

class ListPokemonAdapter(
    private val pokemonsList: List<PokemonDetailsDomain>,
    private val callBack: (PokemonDetailsDomain) -> Unit
) : RecyclerView.Adapter<ListPokemonAdapter.ListPokemonAdapterViewHolder>() {

    private val pokemonsListFiltered = pokemonsList.toMutableList()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPokemonAdapterViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokemonAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = pokemonsListFiltered.size

    override fun onBindViewHolder(holder: ListPokemonAdapterViewHolder, position: Int) {
        holder.bind(pokemonsListFiltered[position])
    }

    inner class ListPokemonAdapterViewHolder(
        itemView: PokemonItemBinding
    ) :
        RecyclerView.ViewHolder(itemView.root) {
        private val imgPokemon: ImageView = itemView.imgPokemon
        private val namePokemon: TextView = itemView.txtNamePokemon
        private val shimmerLayout: ShimmerFrameLayout = itemView.shimmer
        private val allContentItem: MaterialCardView = itemView.allContentItem

        fun bind(pokemonInfo: PokemonDetailsDomain) {

            namePokemon.text = pokemonInfo.name
            imgPokemon.loadImageWithShimmer(
                pokemonInfo.sprites.front_default,
                shimmerLayout
            )

            allContentItem.setOnClickListener {
                callBack(pokemonInfo)
            }
        }
    }

    fun filterList(term: String) {
        pokemonsListFiltered.clear()
        if (term.isNotEmpty()) {
            pokemonsList.filter {
                (it.name.contains(term, ignoreCase = true) || it.types.toString()
                    .contains(term, ignoreCase = true))
            }.let {
                pokemonsListFiltered.addAll(it)
            }
        } else {
            pokemonsListFiltered.addAll(pokemonsList)
        }
        notifyDataSetChanged()
    }
}
