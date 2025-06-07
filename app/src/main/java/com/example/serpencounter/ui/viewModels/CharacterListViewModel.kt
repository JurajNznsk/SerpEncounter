package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.R
import com.example.serpencounter.data.serpCharacter.CharacterRepository
import com.example.serpencounter.data.serpCharacter.SerpCharacter
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel pre správu a zobrazenie zoznamu Entít v encountery.
 * Komunikuje s CharacterRepository a poskytuje aktuálny stav pre UI.
 *
 * @property characterRepository Repozitár zodpovedný za manipuláciu s entitami v databáze
 */
class CharacterListViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    /**
     * Stav UI obsahujúci zoznam všetkých postáv z databázy.
     */
    val charactersUiState: StateFlow<CharactersUiState> =
        characterRepository.getAllCharactersStream().map { CharactersUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = CharactersUiState()
            )

    /**
     * Pridá do databázy predvytvorené postavy (Zombie, Skeleton, Spider).
     */
    fun addDefaultSerpCharacter() {
        viewModelScope.launch {
            characterRepository.insertCharacter(
                SerpCharacter(
                    name = "Zombie",
                    maxHP = 22,
                    armorClass = 8,
                    imageRes = R.drawable.zombie
                )
            )
            characterRepository.insertCharacter(
                SerpCharacter(
                    name = "Skeleton",
                    maxHP = 13,
                    armorClass = 13,
                    imageRes = R.drawable.skeleton
                )
            )
            characterRepository.insertCharacter(
                SerpCharacter(
                    name = "Spider",
                    maxHP = 1,
                    armorClass = 12,
                    imageRes = R.drawable.spider
                )
            )
        }
    }

    /**
     * Vymaže všetky postavy z databázy.
     */
    fun deleteAllSerpCharacters() {
        viewModelScope.launch {
            characterRepository.deleteAllCharacters()
        }
    }

    /**
     * Vymaže konkrétny SerpCharacter z databázy.
     */
    fun deleteSerpCharacter(character: SerpCharacter) {
        viewModelScope.launch {
            characterRepository.deleteCharacter(character)
        }
    }
}

/**
 * Dátová trieda, ktorá reprezentuje stav UI zoznamu postáv.
 *
 * @property charList Zoznam všetkých postáv načítaných z databázy
 */
data class CharactersUiState(
    val charList: List<SerpCharacter> = listOf()
)