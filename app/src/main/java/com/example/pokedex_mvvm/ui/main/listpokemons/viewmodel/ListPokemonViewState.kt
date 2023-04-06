package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain

sealed class ListPokemonViewState() {

    object Loading : ListPokemonViewState()

    class ShowPokemonList(val pokemonList: List<PokemonDetailsDomain>) : ListPokemonViewState()

    class ShowRandomPokemon(val pokemonRandom : PokemonDetailsDomain) : ListPokemonViewState()

    object Error : ListPokemonViewState()
}
