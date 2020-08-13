package com.tekever.pokedex.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.tekever.pokedex.R
import com.tekever.pokedex.databinding.HomeFragmentBinding
import com.tekever.pokedex.presentation.pokemon_list.PokeListFragmentDirections


class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }


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

        binding.genOneButton.setOnClickListener {
            print("Navigate to First Generation List")
            val action = HomeFragmentDirections.actionHomeDestinationToPokeList(1)
            it.findNavController().navigate(action)
        }
        binding.genTwoButton.setOnClickListener {
            print("Navigate to Seccond Generation List")
            val action = HomeFragmentDirections.actionHomeDestinationToPokeList(2)
            it.findNavController().navigate(action)
        }

        binding.genThreeButton.setOnClickListener {
            print("Navigate to Third Generation List")
            val action = HomeFragmentDirections.actionHomeDestinationToPokeList(3)
            it.findNavController().navigate(action)
        }

    }

}