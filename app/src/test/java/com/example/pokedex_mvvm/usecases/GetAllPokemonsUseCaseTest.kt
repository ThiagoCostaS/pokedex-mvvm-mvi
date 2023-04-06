package com.example.pokedex_mvvm.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.data.usecases.GetAllPokemonsUseCase
import com.example.pokedex_mvvm.domain.model.TypeDomain
import com.example.pokedex_mvvm.domain.model.TypeNameDomain
import com.example.pokedex_mvvm.factory.PokemoDetailsFactory.pokemonInfo
import com.example.pokedex_mvvm.factory.PokemonFactory.model
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetAllPokemonsUseCaseTest {

    private val repository = mockk<PokemonRepository>()

    private val useCase = GetAllPokemonsUseCase(repository)


    @Test
    fun `Given return all pokemons when GetAllPokemonsUse Case Is called`() = runTest {
        coEvery { repository.getPokemonList(limit = any(), offSett = any()) } returns model
        coEvery { repository.getPokemonInfo(name = any()) } returns pokemonInfo

        val pokemons = useCase(limit = 0, offset = 0)

        coVerify(exactly = 1) {
            repository.getPokemonList(any(), any())
            repository.getPokemonInfo(any())
        }

        assertEquals(expected = 1, actual = pokemons.size)
        pokemons.forEach {
            assertEquals(expected = "Pikachu", actual = it.name)

            assertEquals(
                expected = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                actual = it.sprites.front_default
            )

            assertTrue(it.types.contains(TypeDomain(TypeNameDomain("fogo"))))
            assertEquals(expected = 10, actual = it.weight)
            assertEquals(expected = 10, actual = it.height)
        }
    }

    @Test
    fun `Given return throws an exception Then should throw the exception`() = runTest {
        coEvery {
            repository.getPokemonList(
                limit = any(),
                offSett = any()
            )
        } throws Throwable("system exception")
        coEvery { repository.getPokemonInfo(name = any()) } throws Throwable("system exception")

        assertFailsWith<Throwable>("any exception") {
            useCase(0, 0)
        }

        coVerify(exactly = 1) {
            repository.getPokemonList(any(), any())
        }
    }
}