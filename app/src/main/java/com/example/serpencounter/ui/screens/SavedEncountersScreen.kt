package com.example.serpencounter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.serpencounter.ui.Screen
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

@Composable
fun SavedEncountersScreen(navController: NavController) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Saved Encounters") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Start.route) }) {
                        Icon(Icons.Default.Menu, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* nový encounter */ }) {
                        Icon(Icons.Default.Add, contentDescription = "New Encounter")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            // Cards s názvom, popisom a tlačidlom na vymazanie
            Text("Uložené stretnutia")
        }
    }
}