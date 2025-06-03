package com.example.serpencounter.ui.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class EntityDatabase : RoomDatabase() {
    abstract fun entityDao(): EntityDao
}