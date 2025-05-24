package com.example.serpencounter.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.serpencounter.ui.Screen

@Composable
fun EncounterScreen(navController: NavController) {
    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class) // TopAppBar je oznaceny ako experimentalny
            TopAppBar(
                title = { Text("Encounter – 3:24") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.Start.route) }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    DropdownMenu(expanded = openDialog, onDismissRequest = { openDialog = false }) {
                        DropdownMenuItem(text = { Text("Save") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("Add Entity") }, onClick = { /* TODO */ })
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = { /* posun dozadu */ }) { Text("←") }
                    TextButton(onClick = { /* posun dopredu */ }) { Text("→") }
                }
            }
        }
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            // Entity Cards tu budú
            Text("Combat management tu bude")
        }
    }
}