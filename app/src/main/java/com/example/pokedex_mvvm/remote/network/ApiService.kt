package com.example.pokedex_mvvm.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiService {

     fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private const val BASE_URL = "https://pokeapi.co/api/v2/"
}