package com.example.serpencounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.serpencounter.data.serpCharacter.SerpCharacter
import com.example.serpencounter.data.serpCharacter.SerpCharacterDao

@Database(entities = [SerpCharacter::class], version = 1, exportSchema = false)
abstract class SerpDatabase : RoomDatabase() {
    abstract fun serpCharacterDao(): SerpCharacterDao

    companion object {
        @Volatile
        private var Instance: SerpDatabase? = null

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