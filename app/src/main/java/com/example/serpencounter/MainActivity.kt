package com.example.serpencounter

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.serpencounter.ui.screens.EncounterScreen
import com.example.serpencounter.ui.screens.StartScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.BLACK
            ),
            navigationBarStyle = SystemBarStyle.dark(
                Color.BLACK
            )
        )
        super.onCreate(savedInstanceState)


        setContent {
            //StartScreen()
            EncounterScreen()
        }
    }
}