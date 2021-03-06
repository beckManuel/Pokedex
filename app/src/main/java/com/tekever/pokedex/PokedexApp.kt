/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tekever.pokedex

import android.app.Application
import com.tekever.pokedex.di.DaggerAppComponent
import com.tekever.pokedex.di.DaggerSingletonComponent


import com.tekever.pokedex.presentation.pokemon_list.PokeListFragment


open class PokedexApp : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent = DaggerAppComponent.create()
    val singletonComponent = DaggerSingletonComponent.create()

}
