package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import kotlin.random.Random

class GetRandomPokemonUseCase(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(): PokemonDetailsDomain {
        val pokemons = pokemonRepository.getPokemonList(limit = TOTAL_POKEMONS, offSett = INITIAL)


        val randomValue = Random.nextInt(INITIAL, TOTAL_POKEMONS)
        return pokemonRepository.getPokemonInfo(pokemons.results[randomValue].name)
    }

    companion object {
       private const val TOTAL_POKEMONS = 1154
       private const val INITIAL = 0
    }
}