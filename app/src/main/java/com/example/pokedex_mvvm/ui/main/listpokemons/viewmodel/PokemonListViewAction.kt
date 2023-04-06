package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

sealed class PokemonListViewAction {

    object GetPokemonsList : PokemonListViewAction()
    object GetRandomPokemon : PokemonListViewAction()
}
