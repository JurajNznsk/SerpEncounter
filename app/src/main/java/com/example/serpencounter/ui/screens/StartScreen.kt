package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.serpencounter.ui.Screen

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Text("SerpEncounter", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(onClick = { navController.navigate(Screen.Encounter.route) }) {
            Text("Encounter")
        }
        OutlinedButton(onClick = { navController.navigate(Screen.EntityList.route) }) {
            Text("Entity List")
        }
        OutlinedButton(onClick = { navController.navigate(Screen.SavedEncounters.route) }) {
            Text("Saved Encounters")
        }
    }
}