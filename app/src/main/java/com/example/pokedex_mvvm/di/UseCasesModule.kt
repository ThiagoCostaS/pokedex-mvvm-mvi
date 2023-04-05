package com.example.pokedex_mvvm.di

import com.example.pokedex_mvvm.data.usecases.GetAllPokemonsUseCase
import com.example.pokedex_mvvm.data.usecases.GetPokemonsInfoUseCase
import com.example.pokedex_mvvm.data.usecases.GetRandomPokemonUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetAllPokemonsUseCase(
            get()
        )
    }

    factory {
        GetPokemonsInfoUseCase(
            get()
        )
    }

    factory {
        GetRandomPokemonUseCase(
            get()
        )
    }
}