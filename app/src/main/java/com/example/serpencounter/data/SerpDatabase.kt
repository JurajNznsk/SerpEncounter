package com.example.serpencounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.serpencounter.data.serpCharacter.SerpCharacter
import com.example.serpencounter.data.serpCharacter.SerpCharacterDao

/**
 * Hlavná databázová trieda pre Room.
 * Obsahuje tabuľku SerpCharakterov [characters].
 * Singleton vzor (vždy len jedna inštancia databázy počas behu aplikácie).
 * Verzia databázy: 1
 * ExportSchema: false (nebude exportovaná do súboru)
 */
@Database(entities = [SerpCharacter::class], version = 1, exportSchema = false)
abstract class SerpDatabase : RoomDatabase() {
    /**
     * Získa DAO pre prískup k SerpCharakterom.
     */
    abstract fun serpCharacterDao(): SerpCharacterDao

    companion object {
        @Volatile // Ensures no problems among coroutines
        private var Instance: SerpDatabase? = null

        /**
         * Získanie jedinej inštancie databázy [SerpDatabase] (ak neexistuje tak sa vytvorí).
         *
         * @param [context] Kontext aplikácie
         * @return [SerpDatabase] Singleton inštancia databázy
         */
        fun getDatabase(context: Context): SerpDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, SerpDatabase::class.java, "character-database")
                    .fallbackToDestructiveMigration(false) // Destroys data from tables when no migration path defined (when attempt to migrate)
                    .build()
                    .also { Instance = it } // Stores DB into Instance
            }
        }
    }
}