package com.example.serpencounter.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.serpencounter.SerpEncounterApplication
import com.example.serpencounter.ui.viewModels.CharacterEntryViewModel
import com.example.serpencounter.ui.viewModels.CharacterListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Character Entry View Model
        initializer<CharacterEntryViewModel> {
            CharacterEntryViewModel(serpEncApplication().container.characterRepository)
        }
        // Characters List View Model
        initializer<CharacterListViewModel> {
            CharacterListViewModel(serpEncApplication().container.characterRepository)
        }
    }
}

fun CreationExtras.serpEncApplication(): SerpEncounterApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SerpEncounterApplication)