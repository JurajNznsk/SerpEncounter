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

enum class SerpEncScreens {
    Start,
    Encounter,
    List,
    AddCharacter;
}

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
                onListButtonClicked = { navController.navigate(SerpEncScreens.List.name) }
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
                onAddCharacterClicked = { navController.navigate(SerpEncScreens.AddCharacter.name)}
            )
        }
        composable(route = SerpEncScreens.AddCharacter.name) {
            CharacterEntryScreen(
                onSaveButtonClicked = { navController.popBackStack() },
                onCancelButtonClicked = { navController.popBackStack() }
            )
        }
    }
}















