package com.tekever.pokedex.presentation.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tekever.pokedex.data.model.PokemonDetailModel
import com.tekever.pokedex.data.repositories.PokedexRepository
import com.tekever.pokedex.webservices.AppError
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailViewModel @Inject constructor(private val _repository: PokedexRepository) : ViewModel() {

    val selected = MutableLiveData<String>()
    var  pokemon : LiveData<PokemonDetailModel> = _repository.pokemon
    var error : LiveData<AppError> = _repository.error


    fun getPokemonByID(context: Context?, pokemonName: String) {

        viewModelScope.launch {
            _repository.fetchPokemonByID(context, pokemonName)
        }

    }

}