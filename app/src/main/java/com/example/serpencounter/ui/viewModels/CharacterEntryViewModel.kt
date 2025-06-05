package com.example.serpencounter.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.data.CharacterRepository
import com.example.serpencounter.data.SerpCharacter
import kotlinx.coroutines.launch

class CharacterEntryViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    //TODO: enter of new char to DB
    var name by mutableStateOf("")
    var maxHP by mutableStateOf("")
    var armorClass by mutableStateOf("")
    var imageRes by mutableStateOf(0)

    fun addSerpCharacter() {
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