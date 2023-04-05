package com.example.pokedex_mvvm.domain.mapper

import com.example.pokedex_mvvm.remote.model.Pokemon
import com.example.pokedex_mvvm.remote.model.Result


fun Pokemon.toDomain() = PokemonDomain(
    results = results.map { it.toDomain() }
)

fun Result.toDomain() = PokemonInfoDomain(
    name = name,
    url = url
)