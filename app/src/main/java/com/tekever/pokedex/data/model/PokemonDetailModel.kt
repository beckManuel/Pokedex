package com.tekever.pokedex.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Diogo Bicho on 12/08/2020.
 *
 */
data class PokemonDetailModel(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Form>,
    val height: Int,
    val held_items: List<Any>,
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)

data class Form(
    val name: String,
    val url: String
)


data class Species(
    val name: String,
    val url: String
)

data class Sprites(
    val other: Other
)

class Other(
    @SerializedName("official-artwork")
    val artwork: OfficialArtwork
)

data class OfficialArtwork(
    val front_default: String
)

data class Stat(
    val base_stat: Int,
    val effort: Int
    //val stat: StatX
)

data class Type(
    val slot: Int,
    val type: TypeX
)

data class TypeX(
    val name: String,
    val url: String
)

data class AbilityX(
    val name: String,
    val url: String
)



