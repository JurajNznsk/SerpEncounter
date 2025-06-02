package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.serpencounter.R

@Composable
fun StartScreen() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
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
            OutlinedButton(
                onClick = {}
            ) {
                Text(text = stringResource(id = R.string.saved_encounters_button))
            }
        }
    }
}