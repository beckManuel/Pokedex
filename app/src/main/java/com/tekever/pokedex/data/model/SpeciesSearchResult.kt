package com.tekever.pokedex.data.model

/**
 * Created by Diogo Bicho on 11/08/2020.
 */
data class SpeciesSearchResult(
    val count: Int,
    val next : String,
    val results : Array<PokemonModel>
)