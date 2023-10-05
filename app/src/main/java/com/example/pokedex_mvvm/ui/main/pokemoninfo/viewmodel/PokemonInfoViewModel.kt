package com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.core.runCatchingWithFlow
import com.example.pokedex_mvvm.data.usecases.GetPokemonsInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonInfoViewModel(
    private val getPokemonsInfoUseCase: GetPokemonsInfoUseCase
) : ViewModel() {

   private val viewState_ = MutableStateFlow<PokemonInfoViewState?>(
            null
    )

    val viewState : StateFlow<PokemonInfoViewState?> = viewState_


    fun dispachAction(action: PokemonInfoViewAction) {
        when(action){
            is PokemonInfoViewAction.GetPokemonInfo -> getPokemonInfo(action.name)
        }
    }

    private fun getPokemonInfo(name: String) = viewModelScope.launch( Dispatchers.Default) {
        viewState_.emit(PokemonInfoViewState.Loading)
        runCatchingWithFlow(

            execute = {
                getPokemonsInfoUseCase(name)
            },
            onFailure = {
                viewState_.emit(PokemonInfoViewState.Error)
            },
            onSuccess = { pokemonInfo ->
                pokemonInfo.collect{
                    viewState_.emit(PokemonInfoViewState.ShowInfoPokemon(it))
                }

            }
        )
    }
}
