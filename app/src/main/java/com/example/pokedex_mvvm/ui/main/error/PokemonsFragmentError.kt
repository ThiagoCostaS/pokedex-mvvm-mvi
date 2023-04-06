package com.example.pokedex_mvvm.ui.main.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.databinding.FragmentPokemonErrorBinding

class PokemonsFragmentError : Fragment() {
    private lateinit var binding: FragmentPokemonErrorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
    }

    private fun onBackPressed() {
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_pokemonsFragmentError_to_listPokemonsFragment)
        }
    }
}