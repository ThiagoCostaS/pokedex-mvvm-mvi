package com.example.pokedex_mvvm.di

import com.example.pokedex_mvvm.remote.network.ApiService
import com.example.pokedex_mvvm.remote.network.PokemonServices
import org.koin.dsl.module
import retrofit2.Retrofit


val retrofitModule = module {
    single { ApiService.initRetrofit() }
    single { get<Retrofit>().create(PokemonServices::class.java) }
}