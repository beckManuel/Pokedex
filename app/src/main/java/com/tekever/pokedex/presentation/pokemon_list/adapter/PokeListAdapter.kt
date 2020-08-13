package com.tekever.pokedex.presentation.pokemon_list.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.tekever.pokedex.R
import com.tekever.pokedex.data.model.PokemonModel
import com.tekever.pokedex.data.model.SpeciesSearchResult
import com.tekever.pokedex.presentation.pokemon_list.PokeListFragmentDirections



class PokeListAdapter() : RecyclerView.Adapter<PokeListAdapter.ViewHolder>() {

    private var values: Array<PokemonModel> = emptyArray()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
       // holder.idView.text = item.name
        holder.contentView.text = item.name.toUpperCase()
        holder.constraintView.setOnClickListener {
            //TODO: separate and the pokemon name
            val action = PokeListFragmentDirections.actionPokeListDestinationToDestinationDetail(item.name)
            it.findNavController().navigate(action)
        };
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        val contentView: TextView = view.findViewById(R.id.content)
        val constraintView: View = view.findViewById(R.id.constraintView)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

    internal fun setItems(games: SpeciesSearchResult) {
        this.values = games.results
        notifyDataSetChanged()

    }
}

