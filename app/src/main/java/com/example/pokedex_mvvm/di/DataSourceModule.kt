package com.example.pokedex_mvvm.di

import com.example.pokedex_mvvm.remote.network.repository.PokemonDataSourceRemote
import com.example.pokedex_mvvm.remote.source.PokemonDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<PokemonDataSource> {
        PokemonDataSourceRemote(
            get()
        )
    }
}