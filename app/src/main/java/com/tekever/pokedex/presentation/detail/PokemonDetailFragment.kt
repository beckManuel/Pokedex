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
import com.squareup.picasso.Picasso
import com.tekever.pokedex.R
import com.tekever.pokedex.databinding.PokemonDetailFragmentBinding
import com.tekever.pokedex.di.DaggerAppComponent
import com.tekever.pokedex.presentation.pokemon_list.PokeListFragment
import com.tekever.pokedex.webservices.DownloadImageTask
import java.lang.Thread.sleep
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
    lateinit var picasso: Picasso

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        picasso = Picasso.get()
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

        val nametextView :TextView = binding.nameView
        val idView :TextView = binding.idView
        val heightView :TextView = binding.heightView
        val weightView :TextView = binding.weightView
        val typeView :TextView = binding.typeView
        val imageView : ImageView = binding.imageView
        val expView : TextView = binding.baseExpView
        val downloadImageTask = DownloadImageTask(imageView)



        //call viewModel, set observers
        viewModel.getPokemonByID(context = this.context, pokemonName = pokemonName)
        viewModel.pokemon.observe(viewLifecycleOwner) {
            picasso
                .load(it.sprites.other.artwork.front_default)
                .noFade()
                .resize(300,300)
                .centerCrop()
                .into(imageView)
            nametextView.text = it.name.capitalize()
            idView.text = it.id.toString()
            heightView.text = it.height.toString()
            weightView.text = it.weight.toString()
            var types = ""
            it.types.forEach { type ->
                var newType =type.type.name.capitalize()
                types += " $newType"
            }
            typeView.text = types
            expView.text = it.base_experience.toString()


            //set progress bar invisible
            binding.progressBar.visibility = View.INVISIBLE
            //set data view visible
            binding.layoutView.visibility = View.VISIBLE

        }



    }

}