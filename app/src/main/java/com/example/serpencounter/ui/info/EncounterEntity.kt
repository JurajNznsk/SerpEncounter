package com.example.serpencounter.ui.info

data class EncounterEntity(
    val name: String,
    val initiative: Int,
    val currentHP: Int,
    val maxHP: Int,
    val armorClass: Int,
    val imageRes: Int
)

// Class that helps me put different items into Encounter Content List
sealed class EncounterListItem {
    data class EntityItem(val entity: EncounterEntity): EncounterListItem()
    data class RoundItem(val name: String = ""): EncounterListItem()
}
