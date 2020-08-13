package com.tekever.pokedex.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.observe
import com.squareup.picasso.Picasso
import com.tekever.pokedex.R
import com.tekever.pokedex.data.model.PokemonDetailModel
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
        arguments?.let { pokemonName = PokemonDetailFragmentArgs.fromBundle(it).pokemonName }
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.pokemon_detail_fragment, container, false)
        DaggerAppComponent.create().inject(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setBindings()
        setViewModelObservers()

    }

    private fun setViewModelObservers() {
        viewModel.getPokemonByID(context = this.context, pokemonName = pokemonName)
        viewModel.pokemon.observe(viewLifecycleOwner) {
            setViewData(it)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            val toast = Toast.makeText(
                this.context,
                "Something went wrong",
                Toast.LENGTH_SHORT
            )
            toast.show()
        }
    }

    private fun setViewData(it: PokemonDetailModel) {
        picasso
            .load(it.sprites.other.artwork.front_default)
            .noFade()
            .resize(300, 300)
            .centerCrop()
            .into(binding.imageView)

        binding.nameView.text = it.name.capitalize()
        binding.idView.text = it.id.toString()
        binding.heightView.text = it.height.toString()
        binding.weightView.text = it.weight.toString()
        binding.baseExpView.text = it.base_experience.toString()
        var types = ""
        it.types.forEach { type ->
            var newType = type.type.name.capitalize()
            types += " $newType"
        }
        binding.typeView.text = types

        //set progress bar invisible
        binding.progressBar.visibility = View.INVISIBLE
        //set data view visible
        binding.layoutView.visibility = View.VISIBLE

    }

    private fun setBindings() {
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

}