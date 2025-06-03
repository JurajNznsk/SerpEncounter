package com.example.serpencounter.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entities")
data class Entity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val maxHP: Int,
    val armorClass: Int,
    val imageRes: Int
)