package com.example.pokedex_mvvm.ui.main.listpokemons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.pokedex_mvvm.customViews.DialogRandomPokemon
import com.example.pokedex_mvvm.databinding.CustomDialogPokemonRandomBinding
import com.example.pokedex_mvvm.databinding.FragmentListPokemonsBinding
import com.example.pokedex_mvvm.domain.mapper.PokemonInfoDomain
import com.example.pokedex_mvvm.ui.main.listpokemons.adapter.ListPokemonAdapter
import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewAction
import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewModel
import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonsFragment : Fragment() {

    private lateinit var binding: FragmentListPokemonsBinding


    private val viewModel: ListPokemonViewModel by viewModel()


    companion object {
        fun newInstance() = ListPokemonsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListPokemonsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configObserver()
        getListPokemons()
        getPokemonRandom()
    }

    private fun configObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                ListPokemonViewState.Error -> "TODO()"
                ListPokemonViewState.Loading -> showLoading()
                is ListPokemonViewState.ShowPokemonList -> handleListPokemons(viewState.pokemonList)
                is ListPokemonViewState.ShowRandomPokemon -> {
                    showDialogRandomPokemon(viewState.pokemonRandom)
                }
            }
        }
    }

    private fun showLoading() {
        with(binding.shimmerViewContainer) {
            startShimmer()
            showShimmer(true)
        }
    }

    private fun hideLoading() {
        with(binding.shimmerViewContainer) {
            stopShimmer()
            hideShimmer()
        }
    }

    private fun handleListPokemons(listPokemons: List<PokemonInfoDomain>) {
        hideLoading()
        val adapter = ListPokemonAdapter(listPokemons) { itemPokemonInfo ->
            Toast.makeText(requireContext(), "Teste cliquee item", Toast.LENGTH_SHORT).show()
        }

        binding.rvPokemons.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
        }
    }

    private fun showDialogRandomPokemon(pokemonRandom: PokemonInfoDomain) {
        hideLoading()
        val dialogRandomPokemon = DialogRandomPokemon(pokemonRandom)
        dialogRandomPokemon.show(requireActivity().supportFragmentManager, DialogRandomPokemon.TAG)
    }


    private fun getListPokemons() {
        viewModel.dispatchAction(ListPokemonViewAction.GetPokemonsList)
    }

    private fun getPokemonRandom() {
        binding.floatingPokeball.setOnClickListener {
            viewModel.dispatchAction(ListPokemonViewAction.GetRandomPokemon)
        }
    }
}