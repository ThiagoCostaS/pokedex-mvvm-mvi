package com.example.pokedex_mvvm.domain.mapper

import com.example.pokedex_mvvm.domain.model.*
import com.example.pokedex_mvvm.remote.model.PokemonDetails
import com.example.pokedex_mvvm.remote.model.Sprites
import com.example.pokedex_mvvm.remote.model.Type
import com.example.pokedex_mvvm.remote.model.TypeName
import kotlinx.coroutines.flow.flowOf
import java.sql.Types

fun PokemonDetails.toDomain() = flowOf(
        PokemonDetailsDomain(name = name,
                sprites = sprites.toDomain(),
                types = types.map { it.toDomain() },
                weight = weight,
                height = height)
)


fun Sprites.toDomain() = SpritesDomain(
        front_default = front_default
)

fun Type.toDomain() = TypeDomain(
        type = type.toDomain()
)

fun TypeName.toDomain() = TypeNameDomain(
        name = name
)
