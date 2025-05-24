package com.example.serpencounter.ui.theme.Screens

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen (
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val text = "Encounter"
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.widthIn(min = 250.dp)
    ) {
    }
}