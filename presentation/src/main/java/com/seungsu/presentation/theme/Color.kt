package com.seungsu.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import javax.annotation.concurrent.Immutable

@Immutable
data class Colors(
    val Black: Color = Color(0xFF000000),
    val White: Color = Color(0xFFFFFFFF),
    val Gray: Color = Color(0xFFCFC6C6),
    val DarkGray: Color = Color(0xFF465179),
    val Purple: Color = Color(0xFF7F56D9),
    val LightPurple: Color = Color(0x447F56D9),
    val border: Color = Color(0xFFE6E6E6),
    val link: Color = Color(0xFF444444)
)

val LocalDongsaniColors = staticCompositionLocalOf { Colors() }
