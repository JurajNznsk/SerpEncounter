package com.example.serpencounter.data

import android.content.Context
import com.example.serpencounter.data.serpCharacter.CharacterRepository
import com.example.serpencounter.data.serpCharacter.SerpCharacterRepository

/**
 * Rozhranie definujúce kontajner aplikácie, ktorý poskytuje závislosť [CharacterRepository]
 */
interface AppContainer {
    /**
     * Inštancia repozitára pre správu SerpCharacterov v aplikácii.
     */
    val characterRepository: CharacterRepository
}

/**
 * Implementácia [AppContainer], ktorá vytvára a poskytuje rálne inštanie závislosti aplikácie.
 *
 * @param [context] Kontext aplikácia potrebný na získanie databázy.
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Lenivo 'Lazy' inicializovaný [CharacterRepository] použitím DAO z [SerpDatabase].
     * Inicializuje sa až pri prvom prístupe, čím šetrí prostriedky.
     */
    override val characterRepository: CharacterRepository by lazy {
        SerpCharacterRepository(SerpDatabase.getDatabase(context).serpCharacterDao())
    }
}