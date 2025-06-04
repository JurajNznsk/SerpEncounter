package com.example.serpencounter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class SerpCharacter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val maxHP: Int,
    val armorClass: Int,
    val imageRes: Int
)
