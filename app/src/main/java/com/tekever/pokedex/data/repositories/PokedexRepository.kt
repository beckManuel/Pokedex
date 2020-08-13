package com.tekever.pokedex.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tekever.pokedex.data.datasources.PokedexApi
import com.tekever.pokedex.data.model.PokemonDetailModel
import com.tekever.pokedex.data.model.SpeciesSearchResult
import com.tekever.pokedex.webservices.AppError
import javax.inject.Inject


/**
 * Created by Diogo Bicho on 10/08/2020.
 */
class PokedexRepository @Inject constructor() {

    val pokemonSpecies: MutableLiveData<SpeciesSearchResult> = MutableLiveData()
    val pokemon: MutableLiveData<PokemonDetailModel> = MutableLiveData()


    fun fetchAllPokemons(context: Context?, generation: Int) {
        var error: LiveData<AppError> = MutableLiveData()
        PokedexApi(context).fetchAllPokemon(generation,
            {
                pokemonSpecies.postValue(it)
            },
            {
                (error as MutableLiveData).value = it
            }
        )

    }


    fun fetchPokemonByID(context: Context?, pokemonName: String) {
        var error: LiveData<AppError> = MutableLiveData()
        PokedexApi(context).fetchPokemonById(pokemonName,
            {
                pokemon.postValue(it)
            },
            {
                (error as MutableLiveData).value = it
            }
        )

    }

}