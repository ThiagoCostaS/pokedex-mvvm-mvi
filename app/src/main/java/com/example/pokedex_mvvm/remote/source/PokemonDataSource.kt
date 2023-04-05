package com.example.pokedex_mvvm.remote.source

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain


interface PokemonDataSource {
    suspend fun getAllPokemon(
        limit: Int,
        offset: Int
    ): PokemonDomain

    suspend fun getPokemonInfo(name: String): PokemonDetailsDomain
}
