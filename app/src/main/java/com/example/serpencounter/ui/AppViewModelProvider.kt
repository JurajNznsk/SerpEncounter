package com.example.serpencounter.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.serpencounter.SerpEncounterApplication
import com.example.serpencounter.ui.viewModels.CharacterEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Character Entry View Model
        initializer {
            CharacterEntryViewModel(serpEncApplication().container.characterRepository)
        }
    }
}

fun CreationExtras.serpEncApplication(): SerpEncounterApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SerpEncounterApplication)