package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

sealed class ListPokemonViewAction{

    object GetPokemonsList:  ListPokemonViewAction()
    object GetRandomPokemon: ListPokemonViewAction()
}
