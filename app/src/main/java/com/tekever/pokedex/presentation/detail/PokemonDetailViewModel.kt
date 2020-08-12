package com.tekever.pokedex.presentation.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekever.pokedex.data.model.PokemonDetailModel
import com.tekever.pokedex.data.repositories.PokedexRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val _repository: PokedexRepository) : ViewModel() {

    val selected = MutableLiveData<String>()
    var  pokemon : LiveData<PokemonDetailModel>

    init {

        pokemon = _repository.pokemon
    }



    fun getPokemonByID(context: Context?, pokemonName: String) {

        viewModelScope.launch {
            //  _repository.test()
            _repository.fetchPokemonByID(context, pokemonName)
        }


        //_repository.fetchAllPokemons(context)

    }

}