package com.tekever.pokedex.webservices

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.tekever.pokedex.PokedexApp
import com.tekever.pokedex.presentation.MainActivity
import com.tekever.pokedex.presentation.pokemon_list.PokeListFragment

/**
 * Created by Diogo Bicho on 11/08/2020.
 */
class HttpRequests(ctx: Context) {
    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(ctx)

    val gson = Gson()

    inline fun <reified T> get(
        url: String,
        crossinline onSuccess: (T) -> Unit,
        crossinline onError: (AppError) -> Unit
    ) {
        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                val dto = gson.fromJson(response, T::class.java)
                onSuccess(dto)
            },
            Response.ErrorListener { err ->
                onError(AppError(err))
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)

    }

}