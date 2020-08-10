package com.tekever.pokedex.presentation.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.fragment.findNavController
import com.tekever.pokedex.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {

    companion object {
        private const val TRANSITION_DELAY: Long = 3000
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goToMain()
    }

    private fun goToMain() {
        activity?.lifecycleScope?.launch {
            delay(TRANSITION_DELAY)
            findNavController().navigate(ActionOnlyNavDirections(R.id.action_splash_destination_to_home_destination))

        }
    }
}