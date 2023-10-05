package com.example.pokedex_mvvm.data.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import kotlinx.coroutines.flow.Flow

class GetPokemonsInfoUseCase(private val repository: PokemonRepository) {
    suspend operator fun invoke(
        name: String
    ): Flow<PokemonDetailsDomain> =
        repository.getPokemonInfo(name)
}
