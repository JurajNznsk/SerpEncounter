package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serpencounter.R
import com.example.serpencounter.data.SerpCharacter
import com.example.serpencounter.ui.theme.OldLondonFont
import com.example.serpencounter.ui.viewModels.CharacterListViewModel

@Composable
fun EntityListScreen(
    onBackButtonClicked: () -> Unit,
    viewModel: CharacterListViewModel
) {
    val charListUiState by viewModel.charactersUiState.collectAsState()

    Scaffold(
        topBar = { TopListBar(
            onBackButtonClicked = onBackButtonClicked
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
                if (charListUiState.charList.isEmpty())
                {
                    Text(text = "Oops")
                } else {
                    EntityGrid(charListUiState.charList)
                }
            }
        }
    }
}

@Composable
fun TopListBar(
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
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Logo {Serp Encounter}
            Text(
                modifier = Modifier
                    .padding(end = 20.dp),
                text = stringResource(R.string.serp_encounter),
                fontFamily = OldLondonFont,
                color = Color.White,
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun EntityGrid(
    characters: List<SerpCharacter>
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
            EntityListCard(character = character)
        }
    }
}

@Composable
fun EntityListCard(
    character: SerpCharacter
) {
    var showStats by remember { mutableStateOf(false)}

    Card(
        onClick = { showStats = !showStats },
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
    ) {
        if (!showStats) {
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
        } else {
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

                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "HP: ${character.maxHP}")
                    Text(text = "AC: ${character.armorClass}")
                }
                //TODO: play with entity info
            }
        }
    }
}

@Composable
fun BottomListBar() {
    Surface(
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {}
}