package com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel

import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain

sealed class PokemonInfoViewState(){

    object Loading: PokemonInfoViewState()

    class ShowInfoPokemon(val pokemonInfo : PokemonDetailsDomain) : PokemonInfoViewState()

    object Error: PokemonInfoViewState()
}
