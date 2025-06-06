package com.example.serpencounter.data

import android.content.Context
import com.example.serpencounter.data.serpCharacter.CharacterRepository
import com.example.serpencounter.data.serpCharacter.SerpCharacterRepository

interface AppContainer {
    val characterRepository: CharacterRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val characterRepository: CharacterRepository by lazy {
        SerpCharacterRepository(SerpDatabase.getDatabase(context).serpCharacterDao())
    }
}