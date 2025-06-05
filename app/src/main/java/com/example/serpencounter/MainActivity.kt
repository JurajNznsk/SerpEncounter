package com.example.serpencounter

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.serpencounter.data.SerpCharacter
import com.example.serpencounter.ui.AppViewModelProvider
import com.example.serpencounter.ui.viewModels.CharacterListViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.dark(
                Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT // Landscape mode forbidden



        setContent {
            SerpEncApp()

        }
    }
}

@Composable
fun testAddCharacter(character: SerpCharacter) {
    val viewModel: CharacterListViewModel = viewModel(factory = AppViewModelProvider.Factory)
    Column {
        Spacer(Modifier.size(64.dp))
        Button(
            onClick = {
                val newCharacter = SerpCharacter(
                    name = "Zombie",
                    maxHP = 13,
                    armorClass = 10,
                    imageRes = R.drawable.zombie
                )
                viewModel.addSerpCharacter(newCharacter)
            }) {
            Text("Add Character")
        }
    }
}