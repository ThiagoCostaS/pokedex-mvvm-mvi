package com.example.pokedex_mvvm.extensions

import android.content.Context
import android.view.View
import com.example.pokedex_mvvm.utils.PokemonTypeColors

fun View.setBackgroundWithType(
    context: Context,
    type: String
) {
    return setBackgroundColor(PokemonTypeColors.getColor(context, type))
}

