package com.example.serpencounter

import android.app.Application
import com.example.serpencounter.data.AppContainer
import com.example.serpencounter.data.AppDataContainer

/**
 * Trieda aplikácie pre SerpEncounter, ktorá inicializuje hlavný kontajner závislosí [AppContainer].
 * Vstupný bod aplikácie, poskytuje centralizovaný prístup.
 */
class SerpEncounterApplication : Application() {
    /**
     * Hlavný kontajner aplikácie, ktorý poskytuje závislosti.
     * Inicializuje sa v metóde onCreate().
     */
    // lateint - nedelegovaná inicializácia premennej; inicializácia sa odkladá na neskôr.
    lateinit var container: AppContainer

    /**
     * Metóda volaná pri štarte aplikácie.
     */
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}