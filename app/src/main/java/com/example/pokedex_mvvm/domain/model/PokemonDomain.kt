package com.example.pokedex_mvvm.domain.mapper

import java.io.Serializable

data class PokemonDomain(
    val results: List<PokemonInfoDomain>
)

data class PokemonInfoDomain(
    val name: String,
    val url: String
) : Serializable {

    val id = url.split("/".toRegex()).dropLast(1).last().toInt()

    fun imageUrl(): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
    }
}