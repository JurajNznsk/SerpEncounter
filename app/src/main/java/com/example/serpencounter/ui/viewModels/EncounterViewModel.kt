package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.data.CharacterRepository
import com.example.serpencounter.ui.info.EncounterEntity
import com.example.serpencounter.ui.info.EncounterListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EncounterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    // Entity list
    private val _uiEntityList = MutableStateFlow<List<EncounterListItem>>(
        listOf(
            EncounterListItem.RoundItem()
        )
    )
    val entityList: StateFlow<List<EncounterListItem>> = _uiEntityList.asStateFlow()

    // Round number
    private val _uiRoundNumber = MutableStateFlow(1)
    val roundNumber: StateFlow<Int> = _uiRoundNumber.asStateFlow()

    // Add Entity to "encounter" from Database
    fun addEntityToEncounter(serpCharId: Int) {
        viewModelScope.launch {
            val serpCharacter = characterRepository.getCharacterStream(serpCharId).firstOrNull()
            if (serpCharacter != null)
            {
                val entity = EncounterEntity(
                    name = serpCharacter.name,
                    currentHP = serpCharacter.maxHP,
                    maxHP = serpCharacter.maxHP,
                    armorClass = serpCharacter.armorClass,
                    imageRes = serpCharacter.imageRes
                )
                _uiEntityList.update { currentList ->
                    currentList + EncounterListItem.EntityItem(entity)
                }
            }
        }
    }

    fun updateEntity(entity: EncounterEntity) {
        _uiEntityList.update { currentList ->
            currentList.map { item ->
                if (item is EncounterListItem.EntityItem && item.entity.randomID == entity.randomID) {
                    item.copy(entity = entity)
                } else {
                    item
                }
            }
        }
    }

    // Entity list functionality
    fun rotateForward() {
        val currentList = _uiEntityList.value
        if (currentList.isEmpty())
            return
        val mutableList = currentList.toMutableList()
        if (mutableList.firstOrNull() is EncounterListItem.RoundItem) {
            _uiRoundNumber.value++
        }
        val first = mutableList.removeAt(0)
        mutableList.add(first)

        _uiEntityList.value = mutableList
    }

    fun rotateBackwards() {
        val currentList = _uiEntityList.value
        if (currentList.isEmpty())
            return
        val mutableList = currentList.toMutableList()
        if (_uiRoundNumber.value > 1 && mutableList.lastOrNull() is EncounterListItem.RoundItem) {
            _uiRoundNumber.value--
        }
        val last = mutableList.removeAt(mutableList.size - 1)
        mutableList.add(0, last)

        _uiEntityList.value = mutableList
    }

    fun moveEntityUp(entity: EncounterEntity) {
        _uiEntityList.update { currentList ->
            val list = currentList.toMutableList()
            val index = list.indexOfFirst { it is EncounterListItem.EntityItem && it.entity == entity }

            if (index > 0 && list[index - 1] !is EncounterListItem.RoundItem) {
                val temp = list[index]
                list[index] = list[index - 1]
                list[index - 1] = temp
            }

            list
        }
    }

    fun moveEntityDown(entity: EncounterEntity) {
        _uiEntityList.update { currentList ->
            val list = currentList.toMutableList()
            val index = list.indexOfFirst { it is EncounterListItem.EntityItem && it.entity == entity }

            if (index != -1 && index < list.lastIndex && list[index + 1] !is EncounterListItem.RoundItem) {
                val temp = list[index]
                list[index] = list[index + 1]
                list[index + 1] = temp
            }

            list
        }
    }
}