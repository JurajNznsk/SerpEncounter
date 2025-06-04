package com.example.serpencounter.data

import android.content.Context

interface AppContainer {
    val characterRepository: CharacterRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val characterRepository: CharacterRepository by lazy {
        SerpCharacterRepository(CharacterDatabase.getDatabase(context).serpCharacterDao())
    }
}