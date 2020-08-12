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

class PokeListViewModel @Inject constructor(private val _repository: PokedexRepository) : ViewModel() {
    val selected = MutableLiveData<String>()
    var  pokemonSpecies : LiveData<SpeciesSearchResult>

    init {

        pokemonSpecies = _repository.pokemonSpecies
    }


    fun select(item: String) {
        selected.value = item
    }


    fun getAllPokemons(context: Context?) {

        viewModelScope.launch {
          //  _repository.test()
            _repository.fetchAllPokemons(context)
        }


       //_repository.fetchAllPokemons(context)

    }

}