package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single

class GetAllPokemonsUseCase(
        private val repository: PokemonRepository
) {

    operator fun invoke(
            limit: Int,
            offset: Int
    ): Flow<List<PokemonDetailsDomain>> {

        return flow {
            val pokemonList = mutableListOf<PokemonDetailsDomain>()
            repository.getPokemonList(limit, offset).collect { pokemonDomain ->
                pokemonDomain.results.forEach { result ->
                    val pokemonInfo = repository.getPokemonInfo(result.name).single()
                    pokemonList.add(pokemonInfo)
                }
            }
            emit(pokemonList)
        }
    }
}


