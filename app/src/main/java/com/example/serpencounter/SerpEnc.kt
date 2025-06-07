package com.example.serpencounter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.serpencounter.ui.screens.CharacterEntryScreen
import com.example.serpencounter.ui.screens.EncounterScreen
import com.example.serpencounter.ui.screens.EntityListScreen
import com.example.serpencounter.ui.screens.StartScreen
import kotlin.system.exitProcess

/**
 * Enum, ktorý predstavuje všetky obrazovky aplikácie SerpEncounter.
 */
enum class SerpEncScreens {
    Start,
    Encounter,
    List,
    CharacterEntry;
}

/**
 * Hlavná composable funkcia aplikácie.
 *
 * @param navController Navigačný kontroler používaný na prepínanie medzi obrazovkami. Predvolená hodnota = [rememberNavController]
 */
@Composable
fun SerpEncApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SerpEncScreens.Start.name,
        modifier =Modifier
            .fillMaxSize()
    ) {
        composable(route = SerpEncScreens.Start.name) {
            StartScreen(
                onEncounterButtonClicked = { navController.navigate(SerpEncScreens.Encounter.name) },
                onListButtonClicked = { navController.navigate(SerpEncScreens.List.name) },
                onExitButtonClicked = { exitProcess(0) }
            )
        }
        composable(route = SerpEncScreens.Encounter.name) {
            EncounterScreen(
                onBackButtonClicked = { navController.popBackStack() }
            )
        }
        composable(route = SerpEncScreens.List.name) {
            EntityListScreen(
                onBackButtonClicked = { navController.popBackStack() },
                onAddCharacterClicked = { navController.navigate(SerpEncScreens.CharacterEntry.name)}
            )
        }
        composable(route = SerpEncScreens.CharacterEntry.name) {
            CharacterEntryScreen(
                onSaveButtonClicked = { navController.popBackStack() },
                onCancelButtonClicked = { navController.popBackStack() }
            )
        }
    }
}















