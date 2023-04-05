package com.example.pokedex_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.pokedex_mvvm.ui.main.listpokemons.ListPokemonsFragment

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListPokemonsFragment.newInstance())
                .commitNow()
        }
    }
}