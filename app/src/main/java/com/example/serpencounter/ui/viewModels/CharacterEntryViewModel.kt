package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.data.serpCharacter.CharacterRepository
import com.example.serpencounter.data.serpCharacter.SerpCharacter
import kotlinx.coroutines.launch

/**
 * ViewModel pre zadávanie nový postáv do databázy.
 * Zodpovedá za spracovanie vstupov od používateľa a vytvorenie novej postavy SerpEncounter; vloženie postvy do databázy.
 *
 * @property characterRepository Repozitár s prístupom ku práci s databázov
 */
class CharacterEntryViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    /**
     * Vytvorí a uloží nový SerpCharacter do databázy.
     * Pri prázdnom mene sa použije predvolené 'NaN'.
     * Pri prázdnych HP a AC sa použije predvolená 0.
     * Ukladanie prebieha asynchrónne (viewModelScope).
     *
     * @param name Meno novej postavy
     * @param maxHP HP novej postavy
     * @param armorClass AC novej postavy
     * @param imageRes ID obrázka pre vzhľad novej postavy
     */
    fun addSerpCharacter(name: String, maxHP: String, armorClass: String, imageRes: Int) {
        val nameValue = name.trim().ifBlank { "NaN" }
        val hp = maxHP.toIntOrNull() ?: 0
        val ac = armorClass.toIntOrNull() ?: 0

        val newCharacter = SerpCharacter(
            name = nameValue,
            maxHP = hp,
            armorClass = ac,
            imageRes = imageRes
        )

        viewModelScope.launch {
            characterRepository.insertCharacter(newCharacter)
        }
    }
}