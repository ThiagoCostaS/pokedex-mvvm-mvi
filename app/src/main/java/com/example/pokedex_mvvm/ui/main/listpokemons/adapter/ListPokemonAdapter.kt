package com.example.pokedex_mvvm.ui.main.listpokemons.adapter
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.databinding.PokemonItemBinding
import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.card.MaterialCardView

class ListPokemonAdapter(
    private val listPokemons: List<PokemonInfoDomain>,
    private val callBack: (PokemonInfoDomain) -> Unit
) : RecyclerView.Adapter<ListPokemonAdapter.ListPokemonAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListPokemonAdapterViewHolder {
        val binding = PokemonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokemonAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = listPokemons.size

    override fun onBindViewHolder(holder: ListPokemonAdapterViewHolder, position: Int) {
        holder.bind(listPokemons[position])
    }

    inner class ListPokemonAdapterViewHolder(
        itemView: PokemonItemBinding
    ) :
        RecyclerView.ViewHolder(itemView.root) {
        private val imgPokemon: ImageView = itemView.imgPokemon
        private val namePokemon: TextView = itemView.txtNamePokemon
        private val shimmerLayout: ShimmerFrameLayout = itemView.shimmer
        private val allContentItem: MaterialCardView = itemView.allContentItem

        fun bind(pokemonInfo: PokemonInfoDomain) {
            namePokemon.text = pokemonInfo.name
            loadImageWithShimmer(imgPokemon, pokemonInfo.imageUrl(), shimmerLayout)

            allContentItem.setOnClickListener {
                callBack(pokemonInfo)
            }
        }
    }

    fun loadImageWithShimmer(imageView: ImageView, imageUrl: String, shimmer: ShimmerFrameLayout) {
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
}