package com.example.pokedex_mvvm.ui.main.listpokemons

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.customViews.DialogRandomPokemon
import com.example.pokedex_mvvm.databinding.FragmentPokemonsListBinding
import com.example.pokedex_mvvm.domain.model.PokemonDetailsDomain
import com.example.pokedex_mvvm.ui.main.listpokemons.adapter.ListPokemonAdapter
import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewAction
import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewModel
import com.example.pokedex_mvvm.ui.main.listpokemons.viewmodel.ListPokemonViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPokemonsFragment : Fragment() {

    private lateinit var binding: FragmentPokemonsListBinding
    private var searchView: SearchView? = null
    private val viewModel: ListPokemonViewModel by viewModel()
    private lateinit var adapterList: ListPokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonsListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)
        configObserver()
        getListPokemons()
        getPokemonRandom()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        this.searchView = searchItem.actionView as SearchView
        searchView?.queryHint = "Nome/Tipo"
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    adapterList.filterList(newText)
                }
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun configObserver() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                ListPokemonViewState.Error -> goToScreenError()
                ListPokemonViewState.Loading -> showLoading()
                is ListPokemonViewState.ShowPokemonList -> handleListPokemons(viewState.pokemonList)
                is ListPokemonViewState.ShowRandomPokemon ->
                    showDialogRandomPokemon(viewState.pokemonRandom)
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

    private fun goToScreenError() {
        findNavController().navigate(R.id.action_listPokemonsFragment_to_pokemonsFragmentError)
    }

    private fun handleListPokemons(listPokemons: List<PokemonDetailsDomain>) {
        hideLoading()
        adapterList = ListPokemonAdapter(listPokemons) { itemPokemonInfo ->
            val bundle = bundleOf("namePokemon" to itemPokemonInfo.name)
            findNavController().navigate(
                R.id.action_listPokemonsFragment_to_pokemonInfoFragment,
                bundle
            )
        }
        binding.rvPokemons.apply {
            adapter = adapterList
            layoutManager = GridLayoutManager(requireContext(), 2)

        }
    }

    private fun showDialogRandomPokemon(pokemonRandom: PokemonDetailsDomain) {
        hideLoading()
        val dialogRandomPokemon = DialogRandomPokemon(pokemonRandom)
        dialogRandomPokemon.show(requireActivity().supportFragmentManager, DialogRandomPokemon.TAG)
    }


    private fun getListPokemons() {
        viewModel.dispatchAction(ListPokemonViewAction.GetPokemonsList)
    }

    private fun getPokemonRandom() {
        hideLoading()
        binding.floatingPokeball.setOnClickListener {
            viewModel.dispatchAction(ListPokemonViewAction.GetRandomPokemon)
        }
    }
}
