package com.example.serpencounter.ui.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SerpCharacterDao {
    @Query("SELECT * FROM characters")
    fun getAll(): Flow<List<SerpCharacter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: SerpCharacter)

    @Delete
    suspend fun delete(entity: SerpCharacter)
}