package com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel

sealed class PokemonInfoViewAction {
    class GetPokemonInfo(val name: String) : PokemonInfoViewAction()
}
