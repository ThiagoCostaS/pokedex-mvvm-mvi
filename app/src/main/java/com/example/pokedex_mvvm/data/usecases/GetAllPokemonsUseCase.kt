package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain

class GetAllPokemonsUseCase(
    private val repository: PokemonRepository
) {

    suspend operator fun invoke(
        limit: Int,
        offset: Int
    ): List<PokemonDetailsDomain> {
        val result = repository.getPokemonList(limit, offset)

        val pokemonDomain = mutableListOf<PokemonDetailsDomain>()

        result.results.forEach {
            val pokemonInfo = repository.getPokemonInfo(it.name)
            pokemonDomain.add(pokemonInfo)
        }

        return pokemonDomain
    }
}
