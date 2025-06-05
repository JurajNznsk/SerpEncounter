package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
                CenterSaved()
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
                text = stringResource(R.string.serp_encounter),
                fontFamily = OldLondonFont,
                color = Color.White,
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun CenterEntry() {

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






