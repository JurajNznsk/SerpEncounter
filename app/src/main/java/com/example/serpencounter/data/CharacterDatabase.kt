package com.example.serpencounter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SerpCharacter::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun serpCharacterDao(): SerpCharacterDao

    companion object {
        @Volatile
        private var Instance: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CharacterDatabase::class.java, "character-database")
                    .fallbackToDestructiveMigration(false) // Destroys data from tables when no migration path defined (when attempt to migrate)
                    .build()
                    .also { Instance = it } // Stores DB into Instance
            }
        }
    }
}