package com.example.pokedex_mvvm.ui.main.pokemoninfo

import androidx.fragment.app.Fragment
import com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel.PokemonInfoViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonInfoFragment : Fragment() {

    private val viewModel: PokemonInfoViewModel by viewModel()

    companion object {
        fun newInstance() = PokemonInfoFragment()
    }
}