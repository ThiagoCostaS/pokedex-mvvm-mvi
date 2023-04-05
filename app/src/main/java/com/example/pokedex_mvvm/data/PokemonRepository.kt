package com.example.pokedex_mvvm.data

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offSett: Int): PokemonDomain

    suspend fun  getPokemonInfo(name: String) : PokemonDetailsDomain
}