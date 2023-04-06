package com.example.pokedex_mvvm.di

import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.PokemonListViewModel
import com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel.PokemonInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PokemonListViewModel(
            get(),
            get()
        )
    }

    viewModel {
        PokemonInfoViewModel(
            get()
        )
    }
}