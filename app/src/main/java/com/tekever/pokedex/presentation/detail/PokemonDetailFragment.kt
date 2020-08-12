package com.tekever.pokedex.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.tekever.pokedex.R
import com.tekever.pokedex.data.di.DaggerAppComponent
import com.tekever.pokedex.databinding.PokemonDetailFragmentBinding
import com.tekever.pokedex.presentation.pokemon_list.PokeListFragment
import com.tekever.pokedex.webservices.DownloadImageTask
import javax.inject.Inject

class PokemonDetailFragment : Fragment() {


    companion object {
        fun newInstance() = PokeListFragment()
    }

    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var viewModel: PokemonDetailViewModel


    //private val viewModel: PokeListViewModel by viewModels()
    private var _binding: PokemonDetailFragmentBinding? = null

    private val binding get() = _binding!! //FIXME: bang-bang
    private var pokemonName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_detail_fragment, container, false)
        DaggerAppComponent.create().inject(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        arguments?.let {  pokemonName = PokemonDetailFragmentArgs.fromBundle(it).pokemonName }


        //val adapter = PokeListAdapter()
        //binding.list.adapter = adapter
        //binding.list.layoutManager = LinearLayoutManager(this.context)

        val textView :TextView = binding.nameView
        val imageView : ImageView = binding.imageView
        val downloadImageTask = DownloadImageTask(imageView)



        //call viewModel, set observers
        viewModel.getPokemonByID(this.context, pokemonName)
        viewModel.pokemon.observe(viewLifecycleOwner) {
            print(it)
            textView.text = it.name
            val test = downloadImageTask.execute(it.sprites.other.artwork.front_default).get()
            imageView.setImageBitmap(test)

            //adapter.setItems(it)
            // update UI
        }



    }

}