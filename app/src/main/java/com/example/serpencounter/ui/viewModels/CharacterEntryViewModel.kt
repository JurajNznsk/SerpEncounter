package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.data.serpCharacter.CharacterRepository
import com.example.serpencounter.data.serpCharacter.SerpCharacter
import kotlinx.coroutines.launch

class CharacterEntryViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

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