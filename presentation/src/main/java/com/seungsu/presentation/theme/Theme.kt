package com.seungsu.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density


@Composable
fun DongsaniTheme(
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDensity provides Density(density = LocalDensity.current.density, fontScale = 1f),
        LocalDongsaniColors provides Colors()
    ) {
        MaterialTheme {
            content()
        }
    }
}

object DongsaniTheme {
    val color: Colors
        @Composable
        get() = LocalDongsaniColors.current
}
