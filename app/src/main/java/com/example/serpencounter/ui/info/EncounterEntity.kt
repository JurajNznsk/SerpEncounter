package com.example.serpencounter.ui.info

import com.example.serpencounter.R

data class EncounterEntity(
    val entityId: Int,
    val name: String,
    val currentHP: Int,
    val maxHP: Int,
    val armorClass: Int,
    val effects: List<Effect> = listOf(),
    val imageRes: Int
)

// Class that helps me put different items into Encounter Content List
sealed class EncounterListItem {
    data class EntityItem(val entity: EncounterEntity): EncounterListItem()
    data class RoundItem(val name: String = ""): EncounterListItem()
}

enum class EntityImageResources(val imageRes: Int) {
    Zombie(R.drawable.zombie),
    Skeleton(R.drawable.skeleton),
    Spider(R.drawable.spider),
    Default(R.drawable.default_photo);
}
