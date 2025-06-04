package com.example.serpencounter.data

import kotlinx.coroutines.flow.Flow

class SerpCharacterRepository(private val characterDao: SerpCharacterDao) : CharacterRepository {
    override fun getAllCharactersStream(): Flow<List<SerpCharacter>> = characterDao.getAll()

    override fun getCharacterStream(id: Int): Flow<SerpCharacter?> = characterDao.get(id)

    override suspend fun insertCharacter(char: SerpCharacter) = characterDao.insert(char)

    override suspend fun deleteCharacter(char: SerpCharacter) = characterDao.delete(char)

    override suspend fun updateCharacter(char: SerpCharacter) = characterDao.update(char)

}