package com.example.serpencounter.data.serpCharacter

import kotlinx.coroutines.flow.Flow

/**
 * Interface definuje prístup k postavám.
 */
interface CharacterRepository {
    /**
     * Vráti prúd ('Flow') všetkých postáv v databáze.
     * Zmena databázy zmení stav.
     *
     * @return [Flow] Zoznam všetkých postáv
     */
    fun getAllCharactersStream(): Flow<List<SerpCharacter>>

    /**
     * Vráti prúd ('Flow') konkrétnej postavy v databáze podľa ID.
     * Zmena / odstránenie postavy zmení hodnotu.
     *
     * @param id ID hľadanej postavy
     * @return [Flow] Postava alebo null (ak sa postava nenachádza)
     */
    fun getCharacterStream(id: Int): Flow<SerpCharacter?>

    /**
     * Vloženie novej postavy.
     *
     * @param [character] SerpCharacter, ktorý sa má vložiť
     */
    suspend fun insertCharacter(character: SerpCharacter)

    /**
     * Aktualizácia existujúcej postavy.
     *
     * @param [character] SerpCharacter s aktuálnymi hodnotami
     */
    suspend fun updateCharacter(character: SerpCharacter)

    /**
     * Odstránenie konkrétnej postavy.
     *
     * @param [character] SerpCharacter, ktorý sa má odstrániť
     */
    suspend fun deleteCharacter(character: SerpCharacter)

    /**
     * Odstránenie všetkých postáv.
     * Nezvratná operácia.
     */
    suspend fun deleteAllCharacters()
}