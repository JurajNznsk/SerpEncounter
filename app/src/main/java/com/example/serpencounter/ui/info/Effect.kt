package com.example.serpencounter.ui.info

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.serpencounter.R

// TODO: resize images and remove background
enum class EffectType(@StringRes val nameId: Int) {
    Poison(R.string.effect_poison),
    Fire(R.string.effect_fire),
    Slow(R.string.effect_slow)
}

@DrawableRes
fun getEffectIcon(effect: EffectType): Int {
    return when (effect) {
        EffectType.Poison -> R.drawable.poison
        EffectType.Fire -> R.drawable.fire
        EffectType.Slow -> R.drawable.slow
    }
}

data class Effect(
    val name: String,
    val imageRes: Int
)
