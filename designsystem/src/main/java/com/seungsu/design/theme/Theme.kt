package com.seungsu.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
