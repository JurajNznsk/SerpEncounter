package com.example.serpencounter.data.serpCharacter

import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getAllCharactersStream(): Flow<List<SerpCharacter>>
    fun getCharacterStream(id: Int): Flow<SerpCharacter?>
    suspend fun insertCharacter(char: SerpCharacter)
    suspend fun updateCharacter(char: SerpCharacter)
    suspend fun deleteCharacter(char: SerpCharacter)
    suspend fun deleteAllCharacters()
}