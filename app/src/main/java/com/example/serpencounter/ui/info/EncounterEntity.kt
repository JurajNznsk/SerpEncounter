package com.example.serpencounter.ui.info

import com.example.serpencounter.R

/**
 * Dátová trieda, ktorá reprezentuje Entitu pre encounter.
 *
 * @property entityId Jedinečný identifikátor Entity
 * @property name Názov postavy
 * @property currentHP Aktuálne životy v boji (Current HP)
 * @property maxHP Maximálne životy postavy (Max HP)
 * @property armorClass Obranné číslo Entity (AC)
 * @property effects Zoznam aktívnych efektov aplikovaných na Entitu
 * @property imageRes ID drawable zdroja obrázka (reprezentuje výzor)
 */
data class EncounterEntity(
    val entityId: Int,
    val name: String,
    val currentHP: Int,
    val maxHP: Int,
    val armorClass: Int,
    val effects: List<Effect> = listOf(),
    val imageRes: Int
)

/**
 * Pomocná sealed trieda, ktorá reprezentuje položku v Encounter zozname.
 * Zabezpečuje, aby bol v Content Liste prístupný ako Číslo kola tak aj zoznam bojujúcich postáv.
 */
// Class that helps me put different items into Encounter Content List
sealed class EncounterListItem {
    /**
     * Dátová trieda reprezentuje Entity.
     */
    data class EntityItem(val entity: EncounterEntity): EncounterListItem()

    /**
     * Dátová trieda reprezentuje Číslo kola.
     */
    data class RoundItem(val name: String = ""): EncounterListItem()
}

/**
 * Enum, ktorý obsahuje preddefinované obrázkové zdroje pre Entity.
 *
 * @property imageRes ID drawable obrázka (výzor postavy)
 */
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
