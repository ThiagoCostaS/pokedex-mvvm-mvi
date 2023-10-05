package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlin.random.Random

class GetRandomPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): Flow<PokemonDetailsDomain> {
        val pokemons = pokemonRepository.getPokemonList(limit = TOTAL_POKEMONS, offSett = INITIAL)

        val randomValue = Random.nextInt(INITIAL, TOTAL_POKEMONS)

        val pokemonRandomFlow = pokemons.map { pokemonList ->
            return@map pokemonList.results[randomValue].name
        }

        return pokemonRandomFlow.flatMapLatest { pokemonName ->
            return@flatMapLatest pokemonRepository.getPokemonInfo(pokemonName)
        }
    }

    companion object {
       private const val TOTAL_POKEMONS = 1154
       private const val INITIAL = 0
    }
}