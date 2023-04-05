package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain
import kotlin.random.Random

class GetRandomPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): PokemonInfoDomain {
        val pokemons = pokemonRepository.getPokemonList(limit = TOTAL_POKEMONS, offSett = INITIAL)
        val randomValue = Random.nextInt(INITIAL, TOTAL_POKEMONS)

        return pokemons.results[randomValue]
    }

    companion object {
        const val TOTAL_POKEMONS = 1154
        const val INITIAL = 0
    }
}