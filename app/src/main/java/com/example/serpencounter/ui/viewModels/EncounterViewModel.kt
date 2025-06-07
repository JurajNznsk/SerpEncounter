package com.example.serpencounter.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serpencounter.data.serpCharacter.CharacterRepository
import com.example.serpencounter.ui.info.EncounterEntity
import com.example.serpencounter.ui.info.EncounterListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel pre správu logiky encounteru v EncounterScreen.
 *
 * @property characterRepository Repozitár, ktorý umožňuje prístup k postavám v databáze a operácie nad nimi
 */
class EncounterViewModel(private val characterRepository: CharacterRepository) : ViewModel() {
    // EntityList state
    private val _uiEntityList = MutableStateFlow<List<EncounterListItem>>(
        listOf(
            EncounterListItem.RoundItem()
        )
    )

    /**
     * Verejný zoznam [StateFlow] entít a čísla kola encounteru.
     */
    val entityList: StateFlow<List<EncounterListItem>> = _uiEntityList.asStateFlow()

    // Sequence number for entityId
    private var _sequenceNum:Int = 1

    // RoundNumber state
    private val _uiRoundNumber = MutableStateFlow(1)

    /**
     * Verejný [StateFlow] s číslom kola encounteru.
     */
    val roundNumber: StateFlow<Int> = _uiRoundNumber.asStateFlow()

    // Timer state
    private val _isTimerRunning = MutableStateFlow(true)

    /**
     * Informácia o bežiacom čase.
     */
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning.asStateFlow()
    // Timer -> seconds
    private val _timeSeconds = MutableStateFlow(0)

    /**
     * Čas v sekundách.
     */
    val timeSeconds: StateFlow<Int> = _timeSeconds.asStateFlow()
    // Timer logic
    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            _isTimerRunning.collect { isRunning ->
                if (isRunning) {
                    while (_isTimerRunning.value) {
                        delay(1000L)
                        _timeSeconds.update { it + 1 }
                    }
                }
            }
        }
    }

    /**
     * Prepína stav časovača (Pause / Play).
     */
    fun toggleTimer() {
        _isTimerRunning.value = !_isTimerRunning.value
    }

    /**
     * Resetovanie času na 00:00:00.
     */
    fun resetTimer() {
        _timeSeconds.value = 0
    }

    /**
     * Pridá postavu dostupnú v databáze do encounteru.
     *
     * @param serpCharId ID postavy, ktorá sa má pridať
     */
    fun addEntityToEncounter(serpCharId: Int) {
        viewModelScope.launch {
            val serpCharacter = characterRepository.getCharacterStream(serpCharId).firstOrNull()
            if (serpCharacter != null)
            {
                val entity = EncounterEntity(
                    entityId = _sequenceNum,
                    name = serpCharacter.name,
                    currentHP = serpCharacter.maxHP,
                    maxHP = serpCharacter.maxHP,
                    armorClass = serpCharacter.armorClass,
                    imageRes = serpCharacter.imageRes
                )
                _sequenceNum++
                _uiEntityList.update { currentList ->
                    currentList + EncounterListItem.EntityItem(entity)
                }
            }
        }
    }

    /**
     * Odstráni entitu zo zoznamu encounteru.
     *
     * @param entity EncounterEntity, ktorá sa má odstrániť
     */
    fun deleteEntity(entity: EncounterEntity) {
        viewModelScope.launch {
            _uiEntityList.update { currentList ->
                currentList.filterNot {
                    it is EncounterListItem.EntityItem && it.entity == entity
                }
            }
        }
    }

    /**
     * Aktualizácia informácií postavy v zozname.
     *
     * @param entity Upravená Entita, ktorá má nahradiť pôvodnú
     */
    fun updateEntity(entity: EncounterEntity) {
        _uiEntityList.update { currentList ->
            currentList.map { item ->
                if (item is EncounterListItem.EntityItem && item.entity.entityId == entity.entityId) {
                    item.copy(entity = entity)
                } else {
                    item
                }
            }
        }
    }

    /**
     * Presunie prvý prvok zoznamu na koniec; zvýši číslo kola, ak je to potrebné.
     */
    fun rotateForward() {
        val currentList = _uiEntityList.value
        if (currentList.isEmpty())
            return
        val mutableList = currentList.toMutableList()

        // Filter out dead entities (currentHP == 0)
        val aliveEntities = mutableList.filter {
            it !is EncounterListItem.EntityItem || it.entity.currentHP > 0
        }.toMutableList()
        // If first item is RoundItem, increment round
        if (aliveEntities.firstOrNull() is EncounterListItem.RoundItem) {
            _uiRoundNumber.value++
        }
        // Rotate only alive entities if possible
        if (aliveEntities.size > 1) {
            val first = aliveEntities.removeAt(0)
            aliveEntities.add(first)
        }
        // Keep dead entities and round items that were filtered out
        val deadEntities = mutableList.filter {
            it is EncounterListItem.EntityItem && it.entity.currentHP == 0
        }
        // Place alive entities first, then dead entities
        _uiEntityList.value = aliveEntities + deadEntities
    }

    /**
     * Presunie posledný prvok zoznamu na začiatok; zníži číslo kola, ak je to potrebné.
     */
    fun rotateBackwards() {
        val currentList = _uiEntityList.value
        if (currentList.isEmpty())
            return
        val mutableList = currentList.toMutableList()

        // Filter out dead entities (currentHP == 0)
        val aliveEntities = mutableList.filter {
            it !is EncounterListItem.EntityItem || it.entity.currentHP > 0
        }.toMutableList()
        // Decrement round number when last
        if (_uiRoundNumber.value > 1 && aliveEntities.lastOrNull() is EncounterListItem.RoundItem) {
            _uiRoundNumber.value--
        }
        // Rotate only alive entities
        if (aliveEntities.size > 1) {
            val last = aliveEntities.removeAt(aliveEntities.size - 1)
            aliveEntities.add(0, last)
        }
        // Keep dead entities and round items that were filtered out
        val deadEntities = mutableList.filter {
            it is EncounterListItem.EntityItem && it.entity.currentHP == 0
        }
        // Place alive before dead
        _uiEntityList.value = aliveEntities + deadEntities
    }

    /**
     * Presunie Entitu o jednu pozíciu vyššie v zozname.
     *
     * @param entity Entita, ktorá sa má posunúť vyššie
     */
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

    /**
     * Presunie Entitu o jednu pozíciu nižšie v zozname.
     *
     * @param entity Entita, ktorá sa má posunúť nižšie
     */
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