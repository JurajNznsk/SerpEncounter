package com.example.serpencounter.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SerpCharacter::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun serpCharacterDao(): SerpCharacterDao
}