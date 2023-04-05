package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain

class GetPokemonsInfoUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(
        name: String
    ): PokemonDetailsDomain =
        repository.getPokemonInfo(name)
}
