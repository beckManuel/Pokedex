package com.tekever.pokedex.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Diogo Bicho on 07/08/2020.
 */

@Entity(tableName = "pokemons")
class Pokemons(
    @PrimaryKey
    val id: Int,
    val name: String
)



