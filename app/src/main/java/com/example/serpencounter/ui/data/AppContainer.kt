package com.example.serpencounter.ui.data

import android.content.Context

interface AppContainer {
    val characterRepository: CharacterRepository
}

class AppDataContainter(private val context: Context) : AppContainer {
    override val characterRepository: CharacterRepository by lazy {
        SerpCharacterRepository(CharacterDatabase.getDatabase(context).serpCharacterDao())
    }
}