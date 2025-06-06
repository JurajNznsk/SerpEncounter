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
    BARBARIAN(R.drawable.barbarian),
    BARD(R.drawable.bard),
    CLERIC(R.drawable.cleric),
    DRUID(R.drawable.druid),
    RANGER(R.drawable.ranger),
    ROGUE(R.drawable.rogue),
    WARLOCK(R.drawable.warlock),
    WIZARD(R.drawable.wizard),
    ZOMBIE(R.drawable.zombie),
    SKELETON(R.drawable.skeleton),
    SPIDER(R.drawable.spider),
    APE(R.drawable.ape),
    SNAKE(R.drawable.snake),
    DEFAULT(R.drawable.default_photo);
}
