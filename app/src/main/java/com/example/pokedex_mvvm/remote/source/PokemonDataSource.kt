package com.example.pokedex_mvvm.remote.source

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain
import kotlinx.coroutines.flow.Flow


interface PokemonDataSource {
    suspend fun getAllPokemon(
        limit: Int,
        offset: Int
    ): Flow<PokemonDomain>

    suspend fun getPokemonInfo(name: String): Flow<PokemonDetailsDomain>
}
