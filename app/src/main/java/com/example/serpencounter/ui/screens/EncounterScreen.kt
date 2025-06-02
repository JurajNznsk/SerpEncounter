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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

@Composable
fun EncounterScreen() {
    Scaffold(
        topBar = { TopEncBar() },
        bottomBar = { BottomEncBar() }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CenterEnc()
            }
        }
    }
}

@Composable
fun TopEncBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Arrow button
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(48.dp)
            ) {
                Icon (
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Timer
            Timer()

            Spacer(modifier = Modifier.weight(1f))

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
    var time by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(true) }

    // Logic; starts corutine - timer runs only when isRunning
    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000L)
            time++
        }
    }

    Text (
        text = formatTime(time),
        color = Color.White
    )
}

fun formatTime(seconds: Int): String {
    val hour = seconds / 3600
    val min = (seconds % 3600) / 60
    val sec = seconds % 60
    return "%02d:%02d:%02d".format(hour, min, sec)
}

@Composable
fun CenterEnc() {

}

@Composable
fun BottomEncBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Row (
            modifier = Modifier
                .padding(bottom = 50.dp),
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
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )
            }

            Spacer(modifier = Modifier.size(40.dp))

            // Button to control timer
            PlayPauseTimerButton()

            Spacer(modifier = Modifier.size(40.dp))

            // Forward Arrow button
            IconButton(
                onClick = {}
            ) {
                Icon (
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Forward",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
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
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
        )
    }
}