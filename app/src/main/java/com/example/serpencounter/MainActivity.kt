package com.example.serpencounter

import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * Hlavná aktivita aplikácie SerpEncounter.
 * Inicializuje používateľské rozhranie pomocou Jetpack Compose a nastavuje preferencie pre vzhľad systémových lišt a orientáciu obrazovky.
 */
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