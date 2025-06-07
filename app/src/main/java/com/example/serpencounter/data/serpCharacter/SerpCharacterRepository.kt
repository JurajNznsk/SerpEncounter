package com.example.serpencounter.data.serpCharacter

import kotlinx.coroutines.flow.Flow

/**
 * Implementácia [CharacterRepository], ktorý využíva [SerpCharacterDao] na prístup k databáze Room.
 * Most medzi databázovou vrstvou a zvyškom aplikácie (oddeľuje zodpovednoti).
 *
 * @param [characterDao] DAO rozhranie pre prácu so SerpCharacterom
 */
class SerpCharacterRepository(private val characterDao: SerpCharacterDao) : CharacterRepository {
    /**
     * Získa prúd 'Flow' všetkých postáv.
     *
     * @return [Flow] Zoznam všetkých postáv
     */
    override fun getAllCharactersStream(): Flow<List<SerpCharacter>> = characterDao.getAll()

    /**
     * Získa prúd 'Flow' konkrétnej postavy podľa jej ID.
     *
     * @param [id] ID hľadanej postavy
     * @return [Flow] Hľadaná postava alebo null ak sa nenájde
     */
    override fun getCharacterStream(id: Int): Flow<SerpCharacter?> = characterDao.get(id)

    /**
     * Vklad novej postavy.
     *
     * @param [character] Vkladaná postava
     */
    override suspend fun insertCharacter(character: SerpCharacter) = characterDao.insert(character)

    /**
     * Aktualizácia existujúcej postavy.
     *
     * @param [character] Postava, ktorá sa má aktualizovať
     */
    override suspend fun updateCharacter(character: SerpCharacter) = characterDao.update(character)

    /**
     * Odstránenie existujúcej postavy.
     *
     * @param [character] Postava, ktorá sa má odstrániť
     */
    override suspend fun deleteCharacter(character: SerpCharacter) = characterDao.delete(character)

    /**
     * Odstránenie všetkých postáv.
     * Nezvratná operácia.
     */
    override suspend fun deleteAllCharacters() = characterDao.deleteAll()
}