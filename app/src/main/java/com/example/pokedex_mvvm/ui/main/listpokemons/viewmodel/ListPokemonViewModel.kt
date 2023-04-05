package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.core.runCatching
import com.example.pokedex_mvvm.data.usecases.GetAllPokemonsUseCase
import com.example.pokedex_mvvm.data.usecases.GetPokemonsInfoUseCase
import com.example.pokedex_mvvm.data.usecases.GetRandomPokemonUseCase
import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPokemonViewModel(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
    private val getRandomsPokemonsUseCase: GetRandomPokemonUseCase
) : ViewModel() {

    val viewState = MutableLiveData<ListPokemonViewState>()
    private var curPage = PAGE_INITIAL

    fun dispatchAction(action: ListPokemonViewAction) {
        when (action) {
            ListPokemonViewAction.GetPokemonsList -> getPokemonsList()

            ListPokemonViewAction.GetRandomPokemon -> getRandomsPokemons()
        }
    }

    private fun getPokemonsList() = viewModelScope.launch {
        viewState.postValue(ListPokemonViewState.Loading)
        runCatching(
            dispatcher = Dispatchers.Default,
            execute = {
                getAllPokemonsUseCase(
                    limit = PAGE_SIZE,
                    offset = PAGE_SIZE * curPage
                )
            },
            onFailure = {
                viewState.postValue(ListPokemonViewState.Error)
            },
            onSuccess = { pokemon ->
                viewState.postValue(ListPokemonViewState.ShowPokemonList(pokemon.results))
            }
        )
    }

    private fun getRandomsPokemons() = viewModelScope.launch {
        viewState.postValue(ListPokemonViewState.Loading)
        runCatching(
            dispatcher = Dispatchers.Default,
            execute = {
                getRandomsPokemonsUseCase()
            },
            onFailure = {
                viewState.postValue(ListPokemonViewState.Error)
            },
            onSuccess = { pokemonRandom ->
                viewState.postValue(
                    ListPokemonViewState.ShowRandomPokemon(
                        PokemonInfoDomain(
                            pokemonRandom.name,
                            pokemonRandom.url
                        )
                    )
                )
            }
        )
    }

    companion object {
        const val PAGE_SIZE = 20
        const val PAGE_INITIAL = 0
    }
}
