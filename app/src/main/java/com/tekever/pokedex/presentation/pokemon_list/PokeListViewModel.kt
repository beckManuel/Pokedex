package com.tekever.pokedex.presentation.pokemon_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tekever.pokedex.data.repositories.PokedexRepository
import javax.inject.Inject

class PokeListViewModel @Inject constructor(private val _repository: PokedexRepository) : ViewModel() {
    val selected = MutableLiveData<String>()

    fun select(item: String) {
        selected.value = item
    }


    fun getAllPokemons(): LiveData<String> {
        _repository.printTest()
         return selected as LiveData<String>
    }

}