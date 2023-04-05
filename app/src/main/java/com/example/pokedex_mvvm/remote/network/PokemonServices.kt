package com.example.pokedex_mvvm.remote.network

import com.example.pokedex_mvvm.remote.model.Pokemon
import com.example.pokedex_mvvm.remote.model.PokemonDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonServices {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Pokemon

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): PokemonDetails
}