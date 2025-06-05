package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.data.CharacterRepository
import com.example.serpencounter.data.SerpCharacter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterListViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    val charactersUiState: StateFlow<CharactersUiState> =
        characterRepository.getAllCharactersStream().map { CharactersUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = CharactersUiState()
            )
    fun getAllSerpCharacters() = charactersUiState

    fun addSerpCharacter(character: SerpCharacter) {
        viewModelScope.launch {
            characterRepository.insertCharacter(character)
        }
    }

    fun deleteAllSerpCharacters() {
        viewModelScope.launch {
            characterRepository.deleteAllCharacters()
        }
    }
}

data class CharactersUiState(val charList: List<SerpCharacter> = listOf())