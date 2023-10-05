package com.example.pokedex_mvvm.data

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offSett: Int): Flow<PokemonDomain>

    suspend fun getPokemonInfo(name: String) : Flow<PokemonDetailsDomain>
}