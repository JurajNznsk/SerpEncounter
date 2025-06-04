package com.example.serpencounter

import android.app.Application
import com.example.serpencounter.data.AppContainer
import com.example.serpencounter.data.AppDataContainter

class SerpEncounterApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainter(this)
    }
}