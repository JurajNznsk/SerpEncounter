package com.example.serpencounter.data.serpCharacter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) interface pre databázové operácie nad SerpCharakterom.
 */
@Dao
interface SerpCharacterDao {
    /**
     * Výber všetkých postáv zoradených podľa mena vzostupne.
     *
     * @return [Flow] Zoznam všetkých postáv
     */
    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getAll(): Flow<List<SerpCharacter>>

    /**
     * Výber jednej postavy podľa jej ID.
     *
     * @param [charId] ID hľadanej postavy
     * @return [Flow] Postava alebo null ak sa nenachádza
     */
    @Query("SELECT * FROM characters WHERE id = :charId")
    fun get(charId: Int): Flow<SerpCharacter?>

    /**
     * Vklad postavy do databázy.
     * Ak už existuje postava s rovnakým ID, tak sa nahradí.
     *
     * @param [character] Vkladaná postava
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: SerpCharacter)

    /**
     * Aktualizácia existujúcej postavy.
     *
     * @param [character] Postava s aktuálnymi údajmi
     */
    @Update
    suspend fun update(character: SerpCharacter)

    /**
     * Odstránenie postavy.
     *
     * @param [character] Postava, ktorá sa má odstrániť
     */
    @Delete
    suspend fun delete(character: SerpCharacter)

    /**
     * Odstránenie všetkých postáv.
     * Nezvratná operácia.
     */
    @Query("DELETE FROM characters")
    suspend fun deleteAll()
}