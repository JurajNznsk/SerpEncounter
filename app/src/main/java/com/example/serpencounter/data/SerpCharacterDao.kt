package com.example.serpencounter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SerpCharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<SerpCharacter>>

    @Query("SELECT * FROM characters WHERE id = :charId")
    fun get(charId: Int): Flow<SerpCharacter?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: SerpCharacter)

    @Update
    suspend fun update(character: SerpCharacter)

    @Delete
    suspend fun delete(character: SerpCharacter)
}