package com.example.pokedex_mvvm.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.pokedex_mvvm.R

object PokemonTypeColors {
    private val typeColors = mapOf(
        "normal" to R.color.colorNormal,
        "fire" to R.color.colorFire,
        "water" to R.color.colorWater,
        "electric" to R.color.colorElectric,
        "grass" to R.color.colorGrass,
        "ice" to R.color.colorIce,
        "fighting" to R.color.colorFighting,
        "poison" to R.color.colorPoison,
        "ground" to R.color.colorGround,
        "flying" to R.color.colorFlying,
        "psychic" to R.color.colorPsychic,
        "bug" to R.color.colorBug,
        "rock" to R.color.colorRock,
        "ghost" to R.color.colorGhost,
        "dragon" to R.color.colorDragon,
        "dark" to R.color.colorDark,
        "steel" to R.color.colorSteel,
        "fairy" to R.color.colorFairy
    )

    fun getColor(context: Context, type: String): Int {
        return ContextCompat.getColor(context, typeColors[type] ?: R.color.colorNormal)
    }
}