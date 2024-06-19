package com.seungsu.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class Colors(
    val Black: Color = Color(0xFF000000),
    val Gray: Color = Color(0xFFF7F7F7),
    val DarkGray: Color = Color(0xFF465179),
    val border: Color = Color(0xFFE6E6E6),
    val link: Color = Color(0xFF444444),
    val date: Color = Color(0xFF888888)
)

val LocalDongsaniColors = staticCompositionLocalOf { Colors() }
