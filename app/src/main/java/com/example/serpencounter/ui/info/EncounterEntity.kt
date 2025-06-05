package com.example.serpencounter.ui.info

import com.example.serpencounter.R
import java.util.UUID

data class EncounterEntity(
    val randomID: String = UUID.randomUUID().toString(),
    val name: String,
    val currentHP: Int,
    val maxHP: Int,
    val armorClass: Int,
    var effects: List<Effect> = listOf(
            Effect(name = "poison", imageRes = R.drawable.poison),
            Effect(name = "burning", imageRes = R.drawable.fire),
            Effect(name = "slow", imageRes = R.drawable.slow)
        ),
    val imageRes: Int
)

// Class that helps me put different items into Encounter Content List
sealed class EncounterListItem {
    data class EntityItem(val entity: EncounterEntity): EncounterListItem()
    data class RoundItem(val name: String = ""): EncounterListItem()
}
