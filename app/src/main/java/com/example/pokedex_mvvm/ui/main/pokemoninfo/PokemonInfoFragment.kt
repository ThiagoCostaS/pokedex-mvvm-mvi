package com.example.pokedex_mvvm.ui.main.pokemoninfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.databinding.FragmentPokemonInfoBinding
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.extensions.loadImageWithShimmer
import com.example.pokedex_mvvm.ui.main.pokemoninfo.adapter.PokemonTypeAdapter
import com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel.PokemonInfoViewAction
import com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel.PokemonInfoViewModel
import com.example.pokedex_mvvm.ui.main.pokemoninfo.viewmodel.PokemonInfoViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonInfoFragment : Fragment() {

    private val viewModel: PokemonInfoViewModel by viewModel()
    private lateinit var binding: FragmentPokemonInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonInfoBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configObserver()
        backButton()
        arguments?.getString("namePokemon")?.let { getPokemonInfo(it) }
    }

    private fun configObserver() {
        lifecycleScope.launch {
            viewModel.viewState.collect { viewState ->
                when (viewState) {
                    PokemonInfoViewState.Error -> goToScreenError()
                    PokemonInfoViewState.Loading -> showLoading()
                    is PokemonInfoViewState.ShowInfoPokemon -> handlePokemonInfo(viewState.pokemonInfo)
                    null -> Unit
                }
            }
        }
    }

    private fun handlePokemonInfo(pokemonInfo: PokemonDetailsDomain) {
        hideLoading()
        with(binding) {
            imagePokemon.loadImageWithShimmer(pokemonInfo.sprites.front_default, shimmer)
            pokemonName.text = pokemonInfo.name
            rvTypePokemon.apply {
                adapter = PokemonTypeAdapter(pokemonInfo.types)
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
            "${pokemonInfo.weight} ${requireActivity().getString(R.string.kg)}".also {
                txtWeight.text = it
            }
            " ${pokemonInfo.height} ${requireActivity().getString(R.string.metros)}".also {
                txtHeight.text = it
            }

        }
    }

    private fun getPokemonInfo(name: String) {
        viewModel.dispachAction(PokemonInfoViewAction.GetPokemonInfo(name))
    }

    private fun goToScreenError() {
        findNavController().navigate(R.id.action_pokemonInfoFragment_to_pokemonsFragmentError)
    }

    private fun backButton() {
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_pokemonInfoFragment_to_listPokemonsFragment)
        }
    }

    private fun showLoading() {
        with(binding.shimmer) {
            startShimmer()
            showShimmer(true)
        }
    }

    private fun hideLoading() {
        with(binding.shimmer) {
            stopShimmer()
            hideShimmer()
        }
    }
}
