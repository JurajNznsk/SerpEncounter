package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.serpencounter.ui.info.EncounterListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EncounterViewModel() : ViewModel() {
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
}