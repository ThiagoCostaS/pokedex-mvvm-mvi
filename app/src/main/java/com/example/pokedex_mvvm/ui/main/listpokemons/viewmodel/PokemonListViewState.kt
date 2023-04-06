package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain

sealed class PokemonListViewState() {

    object Loading : PokemonListViewState()

    class ShowPokemonList(val pokemonList: List<PokemonDetailsDomain>) : PokemonListViewState()

    class ShowRandomPokemonList(val pokemonRandom: PokemonDetailsDomain) : PokemonListViewState()

    object Error : PokemonListViewState()
}
