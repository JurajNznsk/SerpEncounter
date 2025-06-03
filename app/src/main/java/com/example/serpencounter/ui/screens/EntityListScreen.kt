package com.example.serpencounter.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serpencounter.R
import com.example.serpencounter.ui.info.EncounterEntity
import com.example.serpencounter.ui.theme.OldLondonFont

@Composable
fun EntityListScreen(
    onBackButtonClicked: () -> Unit
) {
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
                val sampleEntities = listOf(
                    EncounterEntity("Zombie", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Drbo", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Vrbo", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Izengrim Nightmoon", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Izengrim Nightmoon", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Izengrim Nightmoon", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Izengrim Nightmoon", 10, 11, 13, 10, R.drawable.zombie),
                    EncounterEntity("Izengrim Nightmoon", 10, 11, 13, 10, R.drawable.zombie),
                )

                EntityGrid(sampleEntities)
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
    entities: List<EncounterEntity>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(entities) { entity ->
            EntityListCard(entity = entity)
        }
    }
}

@Composable
fun EntityListCard(
    entity: EncounterEntity
) {
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Entity looks
            Image(
                painter = painterResource(id = entity.imageRes),
                contentDescription = entity.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            // Entity name
            Text(
                text = entity.name,
                fontSize = 16.sp
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
            .height(50.dp)
    ) {}
}