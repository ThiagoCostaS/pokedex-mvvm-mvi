package com.example.pokedex_mvvm.domain.model

data class PokemonDetailsDomain(
    val name: String,
    val sprites: SpritesDomain,
    val types: List<TypeDomain>,
    val weight: Int,
    val height: Int
)

data class SpritesDomain(
    val front_default: String
)

data class TypeDomain(
    val type: TypeNameDomain
)

data class TypeNameDomain(
    val name: String
)
