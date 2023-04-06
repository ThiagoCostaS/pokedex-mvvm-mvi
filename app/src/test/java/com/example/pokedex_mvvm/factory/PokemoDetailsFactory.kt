package com.example.pokedex_mvvm.factory

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.model.SpritesDomain
import com.example.pokedex_mvvm.domain.model.TypeDomain
import com.example.pokedex_mvvm.domain.model.TypeNameDomain

object PokemoDetailsFactory {

    val pokemonInfo = PokemonDetailsDomain(
        name = "Pikachu",
        sprites = SpritesDomain(
            front_default = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        ),
        types = listOf(
            TypeDomain(
                type = TypeNameDomain("fogo")
            )
        ),
        weight = 10,
        height = 10

    )
}