package com.example.serpencounter.ui.info

import com.example.serpencounter.R
import java.util.UUID

data class EncounterEntity(
    val randomID: String = UUID.randomUUID().toString(),
    val name: String,
    val currentHP: Int,
    val maxHP: Int,
    val armorClass: Int,
    var effects: MutableList<Effect> = mutableListOf(
            // Effect(name = "poison", imageRes = R.drawable.poison),
            // Effect(name = "burning", imageRes = R.drawable.fire),
            // Effect(name = "slow", imageRes = R.drawable.slow)
        ),
    val imageRes: Int
) {
    fun toggleEffect(effectName: String) {
        val existing = effects.find { it.name == effectName }
        if (existing != null) {
            effects.remove(existing)
        } else {
            val newEffect = when (effectName) {
                EffectType.Poison.name -> Effect(EffectType.Poison.name, R.drawable.poison)
                EffectType.Fire.name -> Effect(EffectType.Fire.name, R.drawable.fire)
                EffectType.Slow.name -> Effect(EffectType.Slow.name, R.drawable.slow)
                else -> return
            }
            effects.add(newEffect)
        }
    }
}

// Class that helps me put different items into Encounter Content List
sealed class EncounterListItem {
    data class EntityItem(val entity: EncounterEntity): EncounterListItem()
    data class RoundItem(val name: String = ""): EncounterListItem()
}
