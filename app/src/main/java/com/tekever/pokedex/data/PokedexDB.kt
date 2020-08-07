package com.tekever.pokedex.data

/**
 * Created by Diogo Bicho on 07/08/2020.
 */

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tekever.pokedex.data.dao.PokemonsDao
import com.tekever.pokedex.data.entities.Pokemons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Pokemons::class], version = 5)
abstract class PokedexDB : RoomDatabase() {

    abstract fun gamesDao(): PokemonsDao

    private class PokedexDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var pokemonsDao = database.gamesDao()

                    // Delete all content here.
                    pokemonsDao.deleteAll()

                    // Add sample words.
//                    var game = Games("Jogo do mata", "das0", "asd")
//                    gamesDao.insert(game)
//                    game = Games("Football", "das0", "asd")
//                    gamesDao.insert(game)
//
//                    // TODO: Add your own words!
//                    game = Games("Jogo do mata 2", "das0", "asd")
//                    gamesDao.insert(game)
                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: PokedexDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): PokedexDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokedexDB::class.java,
                    "pokedex_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(PokedexDBCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
