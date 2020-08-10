package com.tekever.pokedex.di

/**
 * Created by Diogo Bicho on 10/08/2020.
 */

import com.tekever.pokedex.presentation.pokemon_list.PokeListFragment
import com.tekever.pokedex.presentation.pokemon_list.PokeListViewModel
import dagger.Component

// Definition of a Dagger component
@Component
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(fragment: PokeListFragment)
    //fun inject(viewModel: PokeListViewModel)
}