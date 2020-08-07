package com.tekever.pokedex.presentation.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.tekever.pokedex.R
import com.tekever.pokedex.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private var _binding: HomeFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        binding.allButton.setOnClickListener {
            print("Navigate to All Pokemon Page")
            findNavController().navigate(ActionOnlyNavDirections(R.id.action_home_destination_to_pokemonListFragment))
        }
        binding.searchByIDButton.setOnClickListener {
            print("Navigate to Search Pokemon Page")
        }

    }

}