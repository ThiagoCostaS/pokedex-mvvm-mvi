package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.mapper.PokemonDomain

class GetAllPokemonsUseCase(
   private val repository: PokemonRepository
) {

    suspend operator fun invoke(
        limit: Int,
        offset: Int
    ): PokemonDomain =
        repository.getPokemonList(limit, offset)
}