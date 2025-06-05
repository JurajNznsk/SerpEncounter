package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serpencounter.R
import com.example.serpencounter.data.SerpCharacter
import com.example.serpencounter.ui.AppViewModelProvider
import com.example.serpencounter.ui.theme.OldLondonFont
import com.example.serpencounter.ui.viewModels.CharacterListViewModel

@Composable
fun EntityListScreen(
    onBackButtonClicked: () -> Unit,
    onAddCharacterClicked: () -> Unit,
    viewModel: CharacterListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val serpCharacterList by viewModel.charactersUiState.collectAsState()

    Scaffold(
        topBar = { TopListBar(
            onBackButtonClicked = onBackButtonClicked,
            onAddCharacterClicked = onAddCharacterClicked,
            onAddDefaultCharactersClicked = { viewModel.addDefaultSerpCharacter() },
            onDeleteAllCharactersClicked = { viewModel.deleteAllSerpCharacters() }
        ) },
        bottomBar = { BottomListBar() }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column {
                EntityGrid(
                    characters = serpCharacterList.charList,
                    viewModel = viewModel
                )
            }
        }
    }
}

@Composable
fun TopListBar(
    onBackButtonClicked: () -> Unit,
    onAddCharacterClicked: () -> Unit,
    onAddDefaultCharactersClicked: () -> Unit,
    onDeleteAllCharactersClicked: () -> Unit
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
                    contentDescription = stringResource(R.string.back_button),
                    tint = Color.White,
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Logo {Serp Encounter}
            Text(
                text = stringResource(R.string.serp_encounter),
                fontFamily = OldLondonFont,
                color = Color.White,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            // Options button
            var expanded by remember { mutableStateOf(false) }
            Box {
                IconButton(
                    onClick = { expanded = true }
                ) {
                    Icon (
                        imageVector = Icons.AutoMirrored.Filled.List,
                        contentDescription = stringResource(R.string.option_button),
                        tint = Color.White,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.add_character_to_db)) },
                        onClick = {
                            expanded = false
                            onAddCharacterClicked()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = stringResource(R.string.add_default_to_db)) },
                        onClick = {
                            expanded = false
                            onAddDefaultCharactersClicked()
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = stringResource(R.string.delete_all_from_db),
                                color = Color.Red
                            )
                        },
                        onClick = {
                            expanded = false
                            onDeleteAllCharactersClicked()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EntityGrid(
    characters: List<SerpCharacter>,
    viewModel: CharacterListViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(characters) { character ->
            EntityListCard(
                character = character,
                onCharacterPressed = { viewModel.deleteSerpCharacter(character) }
            )
        }
    }
}

@Composable
fun EntityListCard(
    character: SerpCharacter,
    onCharacterPressed: (SerpCharacter) -> Unit
) {
    var showStats by remember { mutableStateOf(false)}
    var showDeleteCharacterDialog by remember { mutableStateOf(false) }

    Card(
        // onClick = { showStats = !showStats },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f) // For square shape
            .padding(8.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        showStats = !showStats // short tap
                    },
                    onLongPress = { showDeleteCharacterDialog = true }
                )
            },
    ) {
        if (!showStats && !showDeleteCharacterDialog) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Entity name
                Text(
                    text = character.name,
                    fontSize = 20.sp
                )
                // Entity looks
                Image(
                    painter = painterResource(id = character.imageRes),
                    contentDescription = "${character.name} image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        } else if (showStats && !showDeleteCharacterDialog) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = character.name,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(top = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.hp),
                            fontSize = 24.sp
                        )
                        Text(
                            text = "${character.maxHP}",
                            fontSize = 32.sp,
                            color = Color.Green
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(top = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.ac),
                            fontSize = 24.sp
                        )
                        Text(
                            text = "${character.armorClass}",
                            fontSize = 32.sp,
                            color = Color.Blue
                        )
                    }
                }
                //TODO: play with entity info
            }
        }
        if (showDeleteCharacterDialog) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.detele_char),
                tint = Color.DarkGray,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally)
            )
            AlertDialog(
                onDismissRequest = { showDeleteCharacterDialog = false },
                title = {
                    Text(
                        text = stringResource(R.string.confirm_delete)
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDeleteCharacterDialog = false
                            onCharacterPressed(character)
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.delete_one_from_db),
                            fontSize = 15.sp,
                            color = Color.Red
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDeleteCharacterDialog = false }
                    ) {
                        Text(
                            text = stringResource(R.string.cancel_delete_one),
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun BottomListBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = stringResource(R.string.hold_delete),
                color = Color.LightGray,
                fontSize = 10.sp,
            )
        }
    }
}