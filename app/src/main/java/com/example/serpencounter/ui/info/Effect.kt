package com.example.serpencounter.ui.info

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.serpencounter.R

/**
 * Enum, ktorý reprezentuje typy efektov aplikovateľných na postavy.
 *
 * @property nameId ID reťazca (pre názov efektu)
 */
enum class EffectType(@StringRes val nameId: Int) {
    POISON(R.string.effect_poison),
    FIRE(R.string.effect_fire),
    SLOW(R.string.effect_slow)
}

/**
 * Vráti identifikátor ikony pre daný efekt.
 *
 * @param [effect] Typ efektu, pre ktorý sa má ikona získať
 * @return [R.drawable.nazov] ID drawable zdroja pre ikonu efektu
 */
@DrawableRes
fun getEffectIcon(effect: EffectType): Int {
    return when (effect) {
        EffectType.POISON -> R.drawable.poison
        EffectType.FIRE -> R.drawable.fire
        EffectType.SLOW -> R.drawable.slow
    }
}

/**
 * Dátová trieda, ktorá reprezentuje Efekt s názvom a výzorom.
 *
 * @property name Názov efektu
 * @property imageRes Identifikátor drawable obrázku
 */
data class Effect(
    val name: String,
    val imageRes: Int
)
