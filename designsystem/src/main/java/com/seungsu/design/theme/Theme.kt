package com.seungsu.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density

val LightColorScheme = lightColorScheme(
    primaryContainer = Color.White,
    onPrimaryContainer = Grey90,
    secondaryContainer = Color.White,
    onSecondaryContainer = Grey90,
    surface = Color.White,
    onSurface = Grey90
)

val DarkColorScheme = darkColorScheme(
    primaryContainer = Grey90,
    onPrimaryContainer = Grey05,
    secondaryContainer = Grey80,
    onSecondaryContainer = Grey05,
    surface = Grey90,
    onSurface = Grey05
)

@Composable
fun DongsaniTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val dongsaniColors = if (darkTheme) DarkDongsaniColors else DongsaniColors()

    CompositionLocalProvider(
        LocalDensity provides Density(density = LocalDensity.current.density, fontScale = 1f),
        LocalDongsaniDongsaniColors provides dongsaniColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme
        ) {
            content()
        }
    }
}

object DongsaniTheme {
    val colors: DongsaniColors
        @Composable
        get() = LocalDongsaniDongsaniColors.current
}
