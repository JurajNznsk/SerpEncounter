package com.example.serpencounter.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serpencounter.R
import com.example.serpencounter.ui.theme.OldLondonFont

@Composable
fun StartScreen(
    onEncounterButtonClicked: () -> Unit,
    onListButtonClicked: () -> Unit,
    onExitButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background stone bricks
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column (
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo {Serp}
            Text (
                text = stringResource(id = R.string.serp),
                fontFamily = OldLondonFont,
                fontSize = 120.sp
            )
            // Logo {\nEncounter}
            Text (
                text = stringResource(id = R.string.encounter),
                fontFamily = OldLondonFont,
                fontSize = 100.sp
            )

            Spacer(modifier = Modifier.height(200.dp)) // Spacing between "logo" and buttons

            // Encounter button
            OutlinedButton(
                onClick = onEncounterButtonClicked,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(240.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.encounter_button),
                    fontSize = 23.sp
                )
            }
            // List of Entities button
            OutlinedButton(
                onClick = onListButtonClicked,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(240.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.entity_list_button),
                    fontSize = 23.sp
                )
            }
            // Exit button
            OutlinedButton(
                onClick = onExitButtonClicked,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.DarkGray
                ),
                border = BorderStroke(2.dp, Color.DarkGray),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .padding(8.dp)
                    .height(70.dp)
                    .width(240.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.exit_app_button),
                    fontSize = 23.sp
                )
            }
        }
    }
}