package com.example.pokedex_mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PokemonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    override fun onBackPressed() = Unit
}