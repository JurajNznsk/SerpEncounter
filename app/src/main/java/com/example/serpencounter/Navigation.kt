package com.example.serpencounter

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.serpencounter.ui.screens.*

sealed class Screen(val route: String) {
    object Start : Screen("start")
    object Encounter : Screen("encounter")
    object EntityList : Screen("entity-list")
    object SavedEncounters : Screen("saved-encounters")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Start.route) {
        composable(Screen.Start.route) { StartScreen(navController) }
        composable(Screen.Encounter.route) { EncounterScreen(navController) }
        composable(Screen.EntityList.route) { EntityListScreen(navController) }
        composable(Screen.SavedEncounters.route) { SavedEncountersScreen(navController) }
    }
}