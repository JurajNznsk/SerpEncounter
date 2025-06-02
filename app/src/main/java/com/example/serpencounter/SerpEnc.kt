package com.example.serpencounter

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

enum class SerpEncScreens(@StringRes val title: Int) {
    Start(title = R.string.start_screen),
    Encounter(title = R.string.enc_screen),
    List(title = R.string.list_screen),
    Saved(title = R.string.saved_screen);
}

@Composable
fun SerpEncApp(

) {
    val navController = rememberNavController()
}