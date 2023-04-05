package com.example.pokedex_mvvm.data.repositories

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain
import com.example.pokedex_mvvm.remote.source.PokemonDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class PokemonRepositoryImplementation(private val remoteDataSource: PokemonDataSource) :
    PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offSett: Int): PokemonDomain =
        runBlocking(Dispatchers.IO) {
            remoteDataSource.getAllPokemon(limit, offSett)
        }

    override suspend fun getPokemonInfo(name: String): PokemonDetailsDomain =
        runBlocking(Dispatchers.IO) {
            remoteDataSource.getPokemonInfo(name)
        }
}
