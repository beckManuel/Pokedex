package com.tekever.pokedex.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tekever.pokedex.data.entities.Pokemons

/**
 * Created by Diogo Bicho on 07/08/2020.
 */

@Dao
interface PokemonsDao {

        @Query("SELECT * from pokemons")
        fun getAllPokemons(): LiveData<List<Pokemons>>

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertPokemon(pokemon: Pokemons)

        @Query("DELETE FROM pokemons")
        suspend fun deleteAll()
    }
