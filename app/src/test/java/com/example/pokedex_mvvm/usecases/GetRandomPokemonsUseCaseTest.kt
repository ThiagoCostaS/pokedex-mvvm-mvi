package com.example.pokedex_mvvm.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.data.usecases.GetRandomPokemonUseCase
import com.example.pokedex_mvvm.factory.PokemoDetailsFactory.pokemonInfo
import com.example.pokedex_mvvm.factory.PokemonFactory.modelListTotalPokemon
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GetRandomPokemonsUseCaseTest {

    private val repository = mockk<PokemonRepository>()
    private val useCase = GetRandomPokemonUseCase(repository)

    @Test
    fun `Given return all pokemons when GetAllPokemonsUse Case Is called`() = runTest {
        coEvery {
            repository.getPokemonList(
                limit = any(),
                offSett = any()
            )
        } returns modelListTotalPokemon
        coEvery {
            repository.getPokemonInfo(
                name = any()
            )
        } returns pokemonInfo

        val result = useCase()

        assertEquals(expected = "Pikachu", actual = result.name)
        assertEquals(
            expected = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            actual = result.sprites.front_default
        )
    }

    @Test
    fun `Given return throws an exception Then should throw the exception`() = runTest {
        coEvery {
            repository.getPokemonList(
                limit = any(),
                offSett = any()
            )
        } throws Throwable("system exception")
        coEvery {
            repository.getPokemonInfo(
                name = any()
            )
        } throws Throwable("system exception")


        assertFailsWith<Throwable>("any exception") {
            useCase()
            useCase()
        }
    }
}