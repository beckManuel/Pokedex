package com.tekever.pokedex.data.datasources

import android.content.Context
import android.util.Log
import com.tekever.pokedex.data.model.PokemonDetailModel
import com.tekever.pokedex.data.model.SpeciesSearchResult
import com.tekever.pokedex.webservices.AppError
import com.tekever.pokedex.webservices.HttpRequests


/**
 * Created by Diogo Bicho on 11/08/2020.
 */


var search_name: String = "null"
const val LIMIT = "20"
const val HOST = "https://pokeapi.co/api/v2/"
var SEARCH_SPECIES = "${HOST}pokemon-species?limit=${LIMIT}"
var SEARCH_SPECIES_GEN_1 = "${HOST}pokemon-species?offset=0&limit=151"
var SEARCH_SPECIES_GEN_2 = "${HOST}pokemon-species?offset=151&limit=100"
var SEARCH_SPECIES_GEN_3 = "${HOST}pokemon-species?offset=251&limit=135"
var SEARCH_SPECIE_BY_NAME = "${HOST}/pokemon/%s/"


class PokedexApi(context: Context?) {


    val TAG = this::class.java.name
    val httpRequestes = HttpRequests(context!!)


    fun fetchAllPokemon(
        generation: Int,
        onSuccess: (SpeciesSearchResult) -> Unit,
        onError: (AppError) -> Unit
    ) {
        val url = selectGeneration(generation)

        Log.i(TAG, "Making Request to Uri $url")
        httpRequestes.get(url, onSuccess, onError)
    }


    fun fetchPokemonById(
        pokemonName: String,
        onSuccess: (PokemonDetailModel) -> Unit,
        onError: (AppError) -> Unit
    ) {
        val url = String.format(SEARCH_SPECIE_BY_NAME, pokemonName)

        Log.i(TAG, "Making Request to Uri $url")
        httpRequestes.get(url, onSuccess, onError)

    }

    private fun selectGeneration(generation: Int): String {
        return when (generation) {
            1 -> SEARCH_SPECIES_GEN_1
            2 -> SEARCH_SPECIES_GEN_2
            3 -> SEARCH_SPECIES_GEN_3
            else -> SEARCH_SPECIES_GEN_1


        }
    }
}