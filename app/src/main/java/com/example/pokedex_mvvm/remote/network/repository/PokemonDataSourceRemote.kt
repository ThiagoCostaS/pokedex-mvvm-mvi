package com.example.pokedex_mvvm.remote.network.repository

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain
import com.example.pokedex_mvvm.domain.mapper.toDomain
import com.example.pokedex_mvvm.extensions.getOrThrowDomainError
import com.example.pokedex_mvvm.remote.network.PokemonServices
import com.example.pokedex_mvvm.remote.source.PokemonDataSource
import kotlinx.coroutines.flow.Flow

class PokemonDataSourceRemote(private val service: PokemonServices) : PokemonDataSource {
    override suspend fun getAllPokemon(limit: Int, offset: Int): Flow<PokemonDomain> {
        return runCatching { service.getPokemonList(limit, offset) }
            .getOrThrowDomainError()
            .toDomain()
    }

    override suspend fun getPokemonInfo(name: String): Flow<PokemonDetailsDomain> {
        return runCatching { service.getPokemonInfo(name) }
            .getOrThrowDomainError()
            .toDomain()
    }
}
