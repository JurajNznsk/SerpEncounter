package com.example.serpencounter.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serpencounter.R
import com.example.serpencounter.ui.AppViewModelProvider
import com.example.serpencounter.ui.theme.OldLondonFont
import com.example.serpencounter.ui.viewModels.CharacterEntryViewModel

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

@Composable
fun CenterEntry(
    onSaveButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit
) {
    val viewModel: CharacterEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)

    var name by remember { mutableStateOf("") }
    var maxHP by remember { mutableStateOf("") }
    var armorClass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Add new name
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        text = stringResource(R.string.name_to_add)
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
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Add new max HP
            OutlinedTextField(
                value = maxHP,
                onValueChange = { maxHP = it },
                label = {
                    Text(
                        text = stringResource(R.string.max_hp_to_add)
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
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Add new Armor Class
            OutlinedTextField(
                value = armorClass,
                onValueChange = { armorClass = it },
                label = {
                    Text(
                        text = stringResource(R.string.armor_class_to_add)
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
            )
        }

        // TODO: image picker

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Cancel Button
            Button(
                onClick = { onCancelButtonClicked() },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.DarkGray),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(180.dp)
                    .height(80.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel_button),
                    fontSize = 30.sp
                )
            }
            // Save Button
            Button(
                onClick = {
                    viewModel.addSerpCharacter(name, maxHP, armorClass)
                    onSaveButtonClicked()
                },
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .width(180.dp)
                    .height(80.dp)
            ) {
                Text(
                    text = stringResource(R.string.save_button),
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Composable
fun BottomEntryBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {}
}






