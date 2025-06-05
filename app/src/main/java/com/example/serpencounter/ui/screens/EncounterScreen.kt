package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serpencounter.R
import com.example.serpencounter.data.SerpCharacter
import com.example.serpencounter.ui.AppViewModelProvider
import com.example.serpencounter.ui.info.EncounterEntity
import com.example.serpencounter.ui.info.EncounterListItem
import com.example.serpencounter.ui.viewModels.EncounterViewModel
import kotlinx.coroutines.delay

@Composable
fun EncounterScreen(
    onBackButtonClicked: () -> Unit,
    viewModel: EncounterViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    //Timer
    var timerRunning by remember { mutableStateOf(true) }
    var timeSeconds by remember { mutableStateOf(0) }
    // Timer Logic; starts coroutine - timer runs only when isRunning
    LaunchedEffect(timerRunning) {
        while (timerRunning) {
            delay(1000L)
            timeSeconds++
        }
    }

    val roundNumber by viewModel.roundNumber.collectAsState()
    val entityList by viewModel.entityList.collectAsState()

    Scaffold(
        topBar = { TopEncBar(
            timeSeconds = timeSeconds,
            onBackButtonClicked = onBackButtonClicked
        ) },
        bottomBar = { BottomEncBar(
            isRunning = timerRunning,
            onPlayPauseButtonClicked = { timerRunning = it },
            onForwardButtonClicked = { viewModel.rotateForward() },
            onBackwardButtonClicked = { viewModel.rotateBackwards() }
        ) }
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
                EntityList(
                    roundNum = roundNumber,
                    encListItems = entityList,
                    // TODO: move up and down logic
                    onEntityMoveUp = {},
                    onEntityMoveDown = {}
                )
            }
        }
    }
}

@Composable
fun TopEncBar(
    timeSeconds: Int,
    onBackButtonClicked: () -> Unit
) {
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
                onClick = onBackButtonClicked,
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
            Text (
                text = formatTime(timeSeconds),
                fontSize = 25.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))

            // Settings button
            var expanded by remember { mutableStateOf(false) }
            Box {
                IconButton(
                    onClick = { expanded = true }
                ) {
                    Icon (
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = "More...",
                        tint = Color.White,
                        modifier = Modifier.size(100.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.save_encounter)) },
                        onClick = {
                            // TODO: Save encounter (into saved encounters)
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.add_entity)) },
                        onClick = {
                            // TODO: add entity
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EntitySelectionDialog(
    onAdd: (SerpCharacter) -> Unit,
) {
    Dialog(
        onDismissRequest = {}
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.select_char)
                )

                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

fun formatTime(seconds: Int): String {
    val hour = seconds / 3600
    val min = (seconds % 3600) / 60
    val sec = seconds % 60
    return "%02d:%02d:%02d".format(hour, min, sec)
}

@Composable
fun EntityList(
    roundNum: Int,
    encListItems: List<EncounterListItem>,
    onEntityMoveUp: () -> Unit,
    onEntityMoveDown: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(encListItems) { item ->
            when (item) {
                is EncounterListItem.RoundItem -> RoundEncCard(roundNum = roundNum)
                is EncounterListItem.EntityItem ->
                    EntityEncCard(
                        entity = item.entity,
                        onEntityUpdated = {},
                        onEntityMoveUp = onEntityMoveUp,
                        onEntityMoveDown = onEntityMoveDown
                    )
            }
        }
    }
}

@Composable
fun RoundEncCard(
    roundNum: Int
) {
    Text(
        text = "----------------------" + stringResource(R.string.round) + "#$roundNum ----------------------",
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.DarkGray,
        fontSize = 20.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun EntityEncCard(
    entity: EncounterEntity,
    onEntityUpdated: (EncounterEntity) -> Unit,
    onEntityMoveUp: () -> Unit,
    onEntityMoveDown: () -> Unit
) {
    // Show dialog for when updating entity stats
    var showDialog by remember { mutableStateOf(false) }

    Card(
        onClick = { showDialog = true },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row {
            // Entity looks
            Image(
                painter = painterResource(id = entity.imageRes),
                contentDescription = "${entity.name} image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                // Name + effect
                Row {
                    Text(
                        text = entity.name,
                        fontSize = 25.sp
                    )
                    // TODO: zoznam efektov ktore mozu byt entite pridelene
                }
                // AC + CurrentHP / MaxHP
                Row {
                    Text(
                        text = "AC: ${entity.armorClass}",
                        modifier = Modifier
                            .padding(top = 38.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "HP: ${entity.currentHP}/${entity.maxHP}",
                        modifier = Modifier
                            .padding(end = 4.dp, top = 38.dp)
                    )
                }
                // HP bar
                Row {
                    LinearProgressIndicator(
                        progress = { (entity.currentHP.toFloat() / entity.maxHP).coerceIn(0f, 1f) }, // Ensures 0.0 - 1.0 value
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .padding(end = 4.dp),
                        color = Color(0xFF006400),
                        trackColor = Color.Red,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Entity move up
                IconButton(
                    onClick = onEntityMoveUp
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = stringResource(R.string.move_entity_up)
                    )
                }
                IconButton(
                    onClick = onEntityMoveDown
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = stringResource(R.string.move_entity_down)
                    )
                }
            }
        }
    }

    //Things I want to be able to update
    var curHP by remember { mutableStateOf(entity.currentHP.toString()) }
    var curName by remember { mutableStateOf(entity.name) }

    // TODO: Does not work yet
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val newHP = curHP.toIntOrNull()
                        if (newHP != null) {
                            val updatedEntity = entity.copy(currentHP = newHP)
                            onEntityUpdated(updatedEntity)
                            showDialog = false
                        }
                    }
                ) {
                    Text(stringResource(R.string.save_button))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(stringResource(R.string.cancel_button))
                }
            },
            title = {
                Text(entity.name)
            },
            text = {
                Column {
                    Text(stringResource(R.string.current_hp))
                    TextField(
                        value = curHP,
                        onValueChange = { curHP = it },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    // TODO: Add effects
                }
            }
        )
    }
}

@Composable
fun BottomEncBar(
    isRunning: Boolean,
    onPlayPauseButtonClicked: (Boolean) -> Unit,
    onForwardButtonClicked: () -> Unit,
    onBackwardButtonClicked: () -> Unit
) {
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
                onClick = onBackwardButtonClicked
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
            PlayPauseTimerButton(
                isRunning = isRunning,
                onClicked = { onPlayPauseButtonClicked(!isRunning) }
            )

            Spacer(modifier = Modifier.size(40.dp))

            // Forward Arrow button
            IconButton(
                onClick = onForwardButtonClicked
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
fun PlayPauseTimerButton(
    isRunning: Boolean,
    onClicked: () -> Unit
) {
    IconButton(
        onClick = onClicked
    ) {
        Icon(
            imageVector = if (isRunning) Icons.Default.Clear else Icons.Default.PlayArrow,
            contentDescription = if (isRunning) "Pause" else "Play",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
        )
    }
}