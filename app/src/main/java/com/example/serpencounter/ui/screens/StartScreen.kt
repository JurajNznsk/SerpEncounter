package com.example.serpencounter.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.serpencounter.R

@Composable
fun StartScreen() {
    Column {
        OutlinedButton(
            onClick = {}
        ) {
            Text(text = stringResource(id = R.string.encounter_button))
        }
        OutlinedButton(
            onClick = {}
        ) {
            Text(text = stringResource(id = R.string.entity_list_button))
        }
    }
}