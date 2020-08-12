package com.tekever.pokedex.data.datasources

import android.content.Context
import android.util.Log
import com.tekever.pokedex.data.di.DaggerSingletonComponent
import com.tekever.pokedex.data.model.PokemonDetailModel
import com.tekever.pokedex.data.model.SpeciesSearchResult
import com.tekever.pokedex.presentation.MainActivity
import com.tekever.pokedex.webservices.AppError
import com.tekever.pokedex.webservices.HttpRequests


/**
 * Created by Diogo Bicho on 11/08/2020.
 */


var search_name: String = "null"
const val LIMIT = "10"
const val HOST = "https://pokeapi.co/api/v2/"
var SEARCH_SPECIES = "${HOST}pokemon-species?limit=150"
var SEARCH_SPECIE_BY_NAME = "${HOST}/pokemon/%s/"


class PokedexApi(context: Context?) {


    val TAG = this::class.java.name
    val httpRequestes = HttpRequests(context!!)


    fun fetchAllPokemon(
        name: String,
        onSuccess: (SpeciesSearchResult) -> Unit,
        onError: (AppError) -> Unit
    ) {
        val url = String.format(SEARCH_SPECIES, name)

        Log.i(TAG, "Making Request to Uri $url")
        httpRequestes.get(url, onSuccess, onError)

    }


    fun fetchPokemonById(
        name: String,
        onSuccess: (PokemonDetailModel) -> Unit,
        onError: (AppError) -> Unit
    ) {
        val url = String.format(SEARCH_SPECIE_BY_NAME, name)

        Log.i(TAG, "Making Request to Uri $url")
        httpRequestes.get(url, onSuccess, onError)

    }
}