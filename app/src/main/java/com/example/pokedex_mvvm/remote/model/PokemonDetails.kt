package com.example.pokedex_mvvm.remote.model

data class PokemonDetails(
    val name: String,
    val sprites: Sprites,
    val types: List<Type>,
    val weight: Int,
    val height: Int
)

data class Sprites(
    val front_default: String
)

data class Type(
    val type: TypeName
)

data class TypeName(
    val name: String
)