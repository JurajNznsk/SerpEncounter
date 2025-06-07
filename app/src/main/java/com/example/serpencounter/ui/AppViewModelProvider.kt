package com.example.serpencounter.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.serpencounter.SerpEncounterApplication
import com.example.serpencounter.ui.viewModels.CharacterEntryViewModel
import com.example.serpencounter.ui.viewModels.CharacterListViewModel
import com.example.serpencounter.ui.viewModels.EncounterViewModel

/**
 * Objekt, ktorý inicializuje všetky dostupné ViewModely používané v aplikácii.
 * Používa vlastnú inštanciu aplikácie a jej dependency container na injektovanie závislostí do ViewModelov.
 */
object AppViewModelProvider {
    /**
     * Factory, ktorá difunuje, ako sa majú vytvárať jednotlivé ViewModely.
     */
    val Factory = viewModelFactory {
        // Character Entry View Model
        initializer<CharacterEntryViewModel> {
            CharacterEntryViewModel(serpEncApplication().container.characterRepository)
        }
        // Characters List View Model
        initializer<CharacterListViewModel> {
            CharacterListViewModel(serpEncApplication().container.characterRepository)
        }
        // Encounter Screen View Model
        initializer<EncounterViewModel> {
            EncounterViewModel(serpEncApplication().container.characterRepository)
        }
    }
}

/**
 * Rozšírenie pre [CreationExtras], ktoré umožňuje prístup k inštancii [SerpEncounterApplication] pri vytváraní ViewModelov.
 *
 * @return inštancia aplikácie typu [SerpEncounterApplication]
 * @throws ClassCastException ak aplikácia nie je typu [SerpEncounterApplication]
 */
fun CreationExtras.serpEncApplication(): SerpEncounterApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SerpEncounterApplication)

//TODO: odchytiť výnimku