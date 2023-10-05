package com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.core.runCatchingWithFlow
import com.example.pokedex_mvvm.data.usecases.GetAllPokemonsUseCase
import com.example.pokedex_mvvm.data.usecases.GetRandomPokemonUseCase
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class PokemonListViewModel(
        private val getAllPokemonsUseCase: GetAllPokemonsUseCase,
        private val getRandomsPokemonsUseCase: GetRandomPokemonUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow<PokemonListViewState?>(
            null
    )
    val viewState: StateFlow<PokemonListViewState?> = _viewState

    private var curPage = PAGE_INITIAL

    fun dispatchAction(action: PokemonListViewAction) {
        when (action) {
            PokemonListViewAction.GetPokemonsList -> getPokemonsList()

            PokemonListViewAction.GetRandomPokemon -> getRandomsPokemons()
        }
    }

    private fun getPokemonsList() {
        viewModelScope.launch(Dispatchers.Default) {
            _viewState.emit(PokemonListViewState.Loading)
            runCatchingWithFlow(

                    execute = {
                        getAllPokemonsUseCase(limit = PAGE_SIZE, offset = PAGE_SIZE * curPage)
                    },
                    onFailure = {
                        _viewState.emit(PokemonListViewState.Error)
                    },
                    onSuccess = {
                        it.collect {
                            _viewState.emit(PokemonListViewState.ShowPokemonList(it))
                        }
                    })
        }
    }

    private fun getRandomsPokemons() = viewModelScope.launch(Dispatchers.Default) {
        _viewState.emit(PokemonListViewState.Loading)
        runCatchingWithFlow(

                execute = {
                    getRandomsPokemonsUseCase()
                },
                onFailure = {
                    _viewState.emit(PokemonListViewState.Error)
                },
                onSuccess = { pokemonRandom ->
                    pokemonRandom.collect {
                        _viewState.emit(
                                PokemonListViewState.ShowRandomPokemonList(
                                        PokemonDetailsDomain(
                                                name = it.name,
                                                sprites = it.sprites,
                                                types = it.types,
                                                height = it.height,
                                                weight = it.weight
                                        )
                                )
                        )
                    }

                }
        )
    }

    companion object {
        const val PAGE_SIZE = 25
        const val PAGE_INITIAL = 0
    }
}
