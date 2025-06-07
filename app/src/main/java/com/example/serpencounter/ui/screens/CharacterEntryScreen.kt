package com.example.serpencounter.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serpencounter.R
import com.example.serpencounter.ui.AppViewModelProvider
import com.example.serpencounter.ui.info.EntityImageResources
import com.example.serpencounter.ui.theme.OldLondonFont
import com.example.serpencounter.ui.viewModels.CharacterEntryViewModel

/**
 * Obrazovka pre vytváranie novej postavy SerpCharacter.
 * Obsahuje horný a dolný panel; stredovú časť s formuláron na zadávanie údajov o novej postave.
 *
 * @param onSaveButtonClicked Funkcia zavolaná po stlačení tlačidla 'Uložiť' (Na pridávanie postavy do databázy)
 * @param onCancelButtonClicked Funkcia zavolaná po stlačení tlačidla 'Zrušiť' (Zruší pridávanie [nič sa nepridá])
 */
@Composable
fun CharacterEntryScreen(
    onSaveButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    Scaffold(
        topBar = { TopEntryBar() },
        bottomBar = { BottomEntryBar() }
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
                CenterEntry(
                    onSaveButtonClicked = onSaveButtonClicked,
                    onCancelButtonClicked = onCancelButtonClicked
                )
            }
        }

    }
}

/**
 * Horný panel obrazovky.
 */
@Composable
fun TopEntryBar() {
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
            Spacer(modifier = Modifier.weight(1f))
            // Logo {Serp Encounter}
            Text(
                text = stringResource(R.string.create_serpChar),
                fontFamily = OldLondonFont,
                color = Color.White,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

/**
 * Stredová časť obrazovky.
 * Obsahuje 3 textové formuláre na zadanie informácií (Meno, HP, AC); výber obrázka; tlačidlá 'Uložiť / Zrušiť'.
 *
 * @param onSaveButtonClicked Uloženie postavy do databázy
 * @param onCancelButtonClicked Zrušenie činnosti
 */
@Composable
fun CenterEntry(
    onSaveButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    val viewModel: CharacterEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)

    var name by remember { mutableStateOf("") }
    var maxHP by remember { mutableStateOf("") }
    var armorClass by remember { mutableStateOf("") }
    var selectedImageRes by remember { mutableIntStateOf(R.drawable.default_photo) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp)
        ) {
            item {
                // Add new name
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.name_to_add)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.DarkGray,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.DarkGray,
                        cursorColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 16.dp)
                )
                // Add new max HP
                OutlinedTextField(
                    value = maxHP,
                    onValueChange = { maxHP = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.max_hp_to_add)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.DarkGray,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.DarkGray,
                        cursorColor = Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                )
                // Add new Armor Class
                OutlinedTextField(
                    value = armorClass,
                    onValueChange = { armorClass = it },
                    label = {
                        Text(
                            text = stringResource(id = R.string.armor_class_to_add)
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.DarkGray,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.LightGray,
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.DarkGray,
                        cursorColor = Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                )
            }
            item {
                ImagePickerGrid(
                    onImageSelected = { selectedImageRes = it },
                    selectedImageRes = selectedImageRes
                )
            }
        }
        // Floating Buttons
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .imePadding()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onCancelButtonClicked,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.DarkGray),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = stringResource(R.string.cancel_button), fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    viewModel.addSerpCharacter(name, maxHP, armorClass, selectedImageRes)
                    onSaveButtonClicked()
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
            ) {
                Text(text = stringResource(R.string.save_button), fontSize = 20.sp)
            }
        }
    }
}

/**
 * Grid komponent na výber obrázka (výzor postavy).
 * Vybraný obrázok je zvýraznený.
 *
 * @param onImageSelected Priradí ID obrázku
 * @param selectedImageRes ID drawable obrázku
 */
@Composable
fun ImagePickerGrid(
    onImageSelected: (Int) -> Unit,
    selectedImageRes: Int
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
            .padding(8.dp)
    ) {
        items(EntityImageResources.entries) { image ->
            val isSelected = image.imageRes == selectedImageRes
            Card(
                onClick = { onImageSelected(image.imageRes) },
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected) Color.LightGray else Color.DarkGray
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .size(128.dp)
                    .border(
                        width = if (isSelected) 3.dp else 1.dp,
                        color = if (isSelected) Color.Black else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = image.imageRes),
                    contentDescription = image.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )
            }
        }
    }
}

/**
 * Spodný panel obrazovky (čierny).
 * Bez funkcionality.
 */
@Composable
fun BottomEntryBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {}
}






