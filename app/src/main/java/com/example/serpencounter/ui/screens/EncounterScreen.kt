package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.serpencounter.R

@Composable
fun EncounterScreen() {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column() {
            TopEncBar()
            Spacer(modifier = Modifier.weight(1f))
            BottomEncBar()
        }

    }
}

@Composable
fun TopEncBar() {
    Spacer(modifier = Modifier.height(50.dp))
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row () {
            // Back Arrow button
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
                    .padding()
            ) {
                Icon (
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Red,
                    modifier = Modifier.size(100.dp)
                )
            }
            // Timer
            Timer()
            // Settings button
            IconButton(
                onClick = {}
            ) {
                Icon (
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "More...",
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}

@Composable
fun Timer() {
    Text (
        text = "00:00:00",
        color = Color.White
    )
}

@Composable
fun BottomEncBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Arrow button
            IconButton(
                onClick = {}
            ) {
                Icon (
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            // Button to control timer
            PlayPauseTimerButton()

            // Forward Arrow button
            IconButton(
                onClick = {}
            ) {
                Icon (
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun PlayPauseTimerButton() {
    var isPlaying by remember { mutableStateOf(false) }

    IconButton(onClick = { isPlaying = !isPlaying }) {
        Icon(
            imageVector = if (isPlaying) Icons.Default.Clear else Icons.Default.PlayArrow,
            contentDescription = if (isPlaying) "Pause" else "Play",
            tint = Color.White
        )
    }
}