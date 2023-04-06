package com.example.pokedex_mvvm.ui.main.pokemoninfo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_mvvm.databinding.PokemonTypeItemBinding
import com.example.pokedex_mvvm.domain.model.TypeDomain
import com.example.pokedex_mvvm.extensions.setBackgroundWithType
import com.example.pokedex_mvvm.utils.PokemonTypeColors

class PokemonTypeAdapter(
    private val listTypes: List<TypeDomain>
) : RecyclerView.Adapter<PokemonTypeAdapter.PokemonTypeAdapterViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonTypeAdapterViewHolder {
        val binding =
            PokemonTypeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonTypeAdapterViewHolder(parent.context, binding)
    }

    override fun getItemCount(): Int = listTypes.size

    override fun onBindViewHolder(holder: PokemonTypeAdapterViewHolder, position: Int) {
        holder.bind(listTypes[position])
    }

    inner class PokemonTypeAdapterViewHolder(
        private val context: Context,
        itemView: PokemonTypeItemBinding
    ) :
        RecyclerView.ViewHolder(itemView.root) {

        private val pokemonTypeName: TextView = itemView.pokemonTypeName
        private val pokemonCardType: ConstraintLayout = itemView.cardContentAll


        fun bind(type: TypeDomain) {
            pokemonTypeName.text = type.type.name
            pokemonCardType.setBackgroundWithType(context, type.type.name)

        }
    }

}

