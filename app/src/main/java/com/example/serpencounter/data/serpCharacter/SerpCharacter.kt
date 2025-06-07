package com.example.serpencounter.data.serpCharacter

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Dátová trieda reprezentujúca Postavu (SerpCharacter) v aplikácii.
 * Je mapovaná na tabuľku 'characters' v databáze.
 *
 * @property id Primárny kľúč - generuje sa automaticky
 * @property name Meno (resp. názov) postavy
 * @property maxHP Maximálne životy postavy (Max HP)
 * @property armorClass Obranné číslo postavy (AC); používané pri výpočtoch zásahu
 * @property imageRes ID obrázkového zdroja; reprezentuje výzor postavy
 */
@Entity(tableName = "characters")
data class SerpCharacter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val maxHP: Int,
    val armorClass: Int,
    val imageRes: Int
)
