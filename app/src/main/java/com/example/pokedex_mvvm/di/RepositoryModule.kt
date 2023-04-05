package com.example.pokedex_mvvm.di

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.data.repositories.PokemonRepositoryImplementation
import org.koin.dsl.module

val repositoryModule = module {
    single<PokemonRepository> { PokemonRepositoryImplementation(get()) }
}