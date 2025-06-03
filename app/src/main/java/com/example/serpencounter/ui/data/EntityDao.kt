package com.example.serpencounter.ui.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityDao {
    @Query("SELECT * FROM entities")
    fun getAll(): Flow<List<Entity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: Entity)

    @Delete
    suspend fun delete(entity: Entity)
}