package com.example.serpencounter

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.serpencounter.ui.screens.EncounterScreen
import com.example.serpencounter.ui.screens.EntityListScreen
import com.example.serpencounter.ui.screens.SavedEncounterScreen
import com.example.serpencounter.ui.screens.StartScreen

enum class SerpEncScreens(@StringRes val title: Int) {
    Start(title = R.string.start_screen),
    Encounter(title = R.string.enc_screen),
    List(title = R.string.list_screen),
    Saved(title = R.string.saved_screen);
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
                onListButtonClicked = { navController.navigate(SerpEncScreens.List.name) },
                onSavedButtonClicked = { navController.navigate(SerpEncScreens.Saved.name) }
            )
        }
        composable(route = SerpEncScreens.Encounter.name) {
            EncounterScreen()
        }
        composable(route = SerpEncScreens.List.name) {
            EntityListScreen()
        }
        composable(route = SerpEncScreens.Saved.name) {
            SavedEncounterScreen()
        }
    }



}















