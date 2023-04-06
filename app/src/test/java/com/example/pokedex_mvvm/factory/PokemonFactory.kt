package com.example.pokedex_mvvm.factory

import com.example.pokedex_mvvm.domain.mapper.PokemonDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain

object PokemonFactory {
    val model = PokemonDomain(
        results = listOf(
            PokemonInfoDomain(
                name = "Pikachu",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            )
        )
    )

    val modelListTotalPokemon = PokemonDomain(
        results = (1..1154).map {
            PokemonInfoDomain(
                name = "Pikachu",
                url = "https://pokeapi.co/api/v2/pokemon/1/"
            )
        }
    )
}