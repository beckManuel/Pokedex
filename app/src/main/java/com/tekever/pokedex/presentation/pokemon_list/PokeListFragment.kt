package com.tekever.pokedex.presentation.pokemon_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tekever.pokedex.databinding.PokeListFragmentBinding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekever.pokedex.R
import com.tekever.pokedex.data.di.DaggerAppComponent
import com.tekever.pokedex.presentation.pokemon_list.adapter.PokeListAdapter

import javax.inject.Inject


class PokeListFragment : Fragment() {

    companion object {
        fun newInstance() = PokeListFragment()
    }

    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var viewModel: PokeListViewModel


    //private val viewModel: PokeListViewModel by viewModels()
    private var _binding: PokeListFragmentBinding? = null

    private val binding get() = _binding!! //FIXME: bang-bang

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.poke_list_fragment, container, false)
        DaggerAppComponent.create().inject(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        val adapter = PokeListAdapter()
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this.context)



        //call viewModel, set observers
        viewModel.getAllPokemons(this.context)
        viewModel.pokemonSpecies.observe(viewLifecycleOwner) {
            print(it)
            adapter.setItems(it)
            // update UI
        }

    }

}


