package com.tekever.pokedex.presentation.pokemon_list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekever.pokedex.data.model.SpeciesSearchResult
import com.tekever.pokedex.data.repositories.PokedexRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokeListViewModel @Inject constructor(private val _repository: PokedexRepository) :
    ViewModel() {
    var pokemonSpecies: LiveData<SpeciesSearchResult> = _repository.pokemonSpecies


    fun getAllPokemons(context: Context?, genetation: Int) {
        viewModelScope.launch {
            _repository.fetchAllPokemons(context = context, generation = genetation)
        }


    }

}