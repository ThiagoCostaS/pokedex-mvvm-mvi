package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain

sealed class ListPokemonViewState() {

    object Loading : ListPokemonViewState()

    class ShowPokemonList(val pokemonList: List<PokemonInfoDomain>) : ListPokemonViewState()

    class ShowRandomPokemon(val pokemonRandom : PokemonInfoDomain) : ListPokemonViewState()

    object Error : ListPokemonViewState()
}
