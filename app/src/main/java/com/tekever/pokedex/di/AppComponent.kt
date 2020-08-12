package com.tekever.pokedex.data.di

/**
 * Created by Diogo Bicho on 10/08/2020.
 */

import com.tekever.pokedex.data.datasources.PokedexApi
import com.tekever.pokedex.presentation.MainActivity
import com.tekever.pokedex.presentation.detail.PokemonDetailFragment
import com.tekever.pokedex.presentation.pokemon_list.PokeListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


// Definition of a Dagger component
@Component
interface AppComponent {
    // Classes that can be injected by this Component
    fun inject(fragment: PokeListFragment)
    fun inject(fragment: PokemonDetailFragment)



    //fun inject(viewModel: PokeListViewModel)
}

@Component
@Singleton
interface SingletonComponent {
    fun inject(mainActivity: MainActivity?)

    fun provideContext(pokedexApi: PokedexApi)
}


@Module
internal class MainModule {
    @Provides
    @Singleton
    fun provideContext(): MainActivity {
        return MainActivity()
    }

}