package com.tekever.pokedex.presentation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.tekever.pokedex.PokedexApp
import com.tekever.pokedex.R

import javax.inject.Inject

class MainActivity(): AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }
    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        val id: Int? = findNavController(R.id.nav_host_fragment).currentDestination?.id
        if(id == R.id.home_destination) {
            buildExitDialog()
        } else {
            super.onBackPressed()
        }
    }

    private fun buildExitDialog(){
        val alertDialog = AlertDialog.Builder(this, R.style.Theme_AppCompat_DayNight_Dialog)
        alertDialog.setTitle("Exit?")
        alertDialog.setMessage("Do you wish to exit the Pokédex?")
        alertDialog.setNegativeButton("NO") { _, _ -> findNavController(R.id.nav_host_fragment).popBackStack(R.id.home_destination, false)}
        alertDialog.setPositiveButton("YES") { _, _ -> finishAffinity() }
        alertDialog.show()
    }
}