package com.example.pokedex_mvvm.usecases

import com.example.pokedex_mvvm.data.PokemonRepository
import com.example.pokedex_mvvm.data.usecases.GetPokemonsInfoUseCase
import com.example.pokedex_mvvm.domain.model.TypeDomain
import com.example.pokedex_mvvm.domain.model.TypeNameDomain
import com.example.pokedex_mvvm.factory.PokemoDetailsFactory.pokemonInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GetPokemonInfoUseCaseTest {

    private val repository = mockk<PokemonRepository>()

    private val useCase = GetPokemonsInfoUseCase(repository)


    @Test
    fun `Given return info pokemon when GetInfoPokemonsUseCase Is called`() = runTest {
        coEvery {
            repository.getPokemonInfo(
                name = any()
            )
        } returns pokemonInfo

        val result = useCase("Pikachu")

        assertEquals(expected = "Pikachu", actual = result.name)

        assertEquals(
            expected = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            actual = result.sprites.front_default
        )

        assertTrue(result.types.contains(TypeDomain(TypeNameDomain("fogo"))))
        assertEquals(expected = 10, actual = result.weight)
        assertEquals(expected = 10, actual = result.height)
    }

    @Test
    fun `Given return throwable when GetInfoPokemonUseCase Is called`() = runTest {
        coEvery { repository.getPokemonInfo(name = any()) } throws Throwable("system exception")

        assertFailsWith<Throwable>("any exception") {
            useCase("Pikachu")
        }
    }
}