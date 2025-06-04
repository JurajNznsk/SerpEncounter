package com.example.serpencounter

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.serpencounter.ui.AppViewModelProvider
import com.example.serpencounter.ui.screens.EncounterScreen
import com.example.serpencounter.ui.screens.EntityListScreen
import com.example.serpencounter.ui.screens.SavedEncounterScreen
import com.example.serpencounter.ui.screens.StartScreen
import com.example.serpencounter.ui.viewModels.CharacterListViewModel

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
            EncounterScreen(
                onBackButtonClicked = { navController.popBackStack() }
            )
        }
        composable(route = SerpEncScreens.List.name) {
            Log.d("JAKO", "Before view model")
            val viewM: CharacterListViewModel = viewModel(factory = AppViewModelProvider.Factory)
            Log.d("JAKO", "After view model")
            EntityListScreen(
                onBackButtonClicked = { navController.popBackStack() },
                viewModel = viewM
            )
        }
        composable(route = SerpEncScreens.Saved.name) {
            SavedEncounterScreen(
                onBackButtonClicked = { navController.popBackStack() }
            )
        }
    }
}















