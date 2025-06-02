package com.example.serpencounter

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.example.serpencounter.ui.screens.EncounterScreen
import com.example.serpencounter.ui.screens.EntityListScreen
import com.example.serpencounter.ui.screens.SavedEncounterScreen
import com.example.serpencounter.ui.screens.StartScreen

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

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            StartScreen()
            //EncounterScreen()
            //EntityListScreen()
            //SavedEncounterScreen()
            //SerpEncApp()
        }
    }
}