package com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.core.runCatching
import com.example.pokedex_mvvm.data.usecases.GetPokemonsInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonInfoViewModel(
    private val getPokemonsInfoUseCase: GetPokemonsInfoUseCase
) : ViewModel() {
    val viewState = MutableLiveData<PokemonInfoViewState>()

    fun dispachAction(action: PokemonInfoViewAction) {
        when(action){
            is PokemonInfoViewAction.GetPokemonInfo -> getPokemonInfo(action.name)
        }
    }

    private fun getPokemonInfo(name: String) = viewModelScope.launch {
        viewState.postValue(PokemonInfoViewState.Loading)
        runCatching(
            dispatcher = Dispatchers.Default,
            execute = {
                getPokemonsInfoUseCase(name)
            },
            onFailure = {
                viewState.postValue(PokemonInfoViewState.Error)
            },
            onSuccess = { pokemonInfo ->
                viewState.postValue(PokemonInfoViewState.ShowInfoPokemon(pokemonInfo))
            }
        )
    }
}
