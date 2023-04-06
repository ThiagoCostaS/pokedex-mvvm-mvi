package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.core.runCatching
import com.example.pokedex_mvvm.data.usecases.GetAllPokemonsUseCase
import com.example.pokedex_mvvm.data.usecases.GetRandomPokemonUseCase
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
    private val getRandomsPokemonsUseCase: GetRandomPokemonUseCase
) : ViewModel() {

    val viewState = MutableLiveData<PokemonListViewState>()
    private var curPage = PAGE_INITIAL

    fun dispatchAction(action: PokemonListViewAction) {
        when (action) {
            PokemonListViewAction.GetPokemonsList -> getPokemonsList()

            PokemonListViewAction.GetRandomPokemon -> getRandomsPokemons()
        }
    }

    private fun getPokemonsList() = viewModelScope.launch {
        viewState.postValue(PokemonListViewState.Loading)
        runCatching(
            dispatcher = Dispatchers.Default,
            execute = {
                getAllPokemonsUseCase(
                    limit = PAGE_SIZE,
                    offset = PAGE_SIZE * curPage
                )
            },
            onFailure = {
                viewState.postValue(PokemonListViewState.Error)
            },
            onSuccess = { pokemon ->
                viewState.postValue(PokemonListViewState.ShowPokemonList(pokemon))
            }
        )
    }

    private fun getRandomsPokemons() = viewModelScope.launch {
        viewState.postValue(PokemonListViewState.Loading)
        runCatching(
            dispatcher = Dispatchers.Default,
            execute = {
                getRandomsPokemonsUseCase()
            },
            onFailure = {
                viewState.postValue(PokemonListViewState.Error)
            },
            onSuccess = { pokemonRandom ->
                viewState.postValue(
                    PokemonListViewState.ShowRandomPokemonList(
                        PokemonDetailsDomain(
                            name = pokemonRandom.name,
                            sprites = pokemonRandom.sprites,
                            types = pokemonRandom.types,
                            height = pokemonRandom.height,
                            weight = pokemonRandom.weight
                        )
                    )
                )

            }
        )
    }

    companion object {
        const val PAGE_SIZE = 25
        const val PAGE_INITIAL = 0
    }
}
