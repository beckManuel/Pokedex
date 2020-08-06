package com.tekever.pokedex

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController

class SplashScreenFragment : Fragment() {

    companion object {
        private const val TRANSITION_DELAY:Long = 2500
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToMain()
    }

    private fun goToMain(){

        Handler().postDelayed({
            findNavController().navigate(ActionOnlyNavDirections(R.id.action_splash_destination_to_home_destination))
        }, TRANSITION_DELAY)


    }
}