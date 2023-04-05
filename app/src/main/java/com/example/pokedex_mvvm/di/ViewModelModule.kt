package com.example.pokedex_mvvm.di

import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ListPokemonViewModel(
            get(),
            get()
        )
    }
}