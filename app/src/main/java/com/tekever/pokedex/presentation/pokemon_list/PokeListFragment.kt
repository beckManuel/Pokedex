package com.tekever.pokedex.presentation.pokemon_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.tekever.pokedex.R
import com.tekever.pokedex.databinding.PokeListFragmentBinding
import com.tekever.pokedex.di.DaggerAppComponent
import com.tekever.pokedex.presentation.pokemon_list.adapter.PokeListAdapter
import kotlinx.android.synthetic.main.poke_list_fragment.*
import javax.inject.Inject


class PokeListFragment : Fragment() {

    companion object {
        fun newInstance() = PokeListFragment()
    }

    @Inject
    lateinit var viewModel: PokeListViewModel
    private var generation: Int = 1
    private var _binding: PokeListFragmentBinding? = null
    private val binding get() = _binding!! //FIXME: bang-bang
    private lateinit var adapter: PokeListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { generation = PokeListFragmentArgs.fromBundle(it).generation }
        _binding = DataBindingUtil.inflate(inflater, R.layout.poke_list_fragment, container, false)
        DaggerAppComponent.create().inject(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setBindings()
        setViewModelObservers()
    }

    private fun setViewModelObservers() {
        viewModel.getAllPokemons(context = this.context, genetation = generation)
        viewModel.pokemonSpecies.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

    }

    private fun setBindings() {
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        adapter = PokeListAdapter()
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this.context)
    }

}


