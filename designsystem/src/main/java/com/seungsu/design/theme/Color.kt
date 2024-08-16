package com.seungsu.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Grey05 = Color(0xFFF7F7F7)
val Grey10 = Color(0xFFECECEC)
val Grey20 = Color(0xFFDFDFDF)
val Grey30 = Color(0xFFCFCFCF)
val Grey40 = Color(0xFFBCBCBC)
val Grey50 = Color(0xFFA5A5A5)
val Grey60 = Color(0xFF8A8A8A)
val Grey70 = Color(0xFF5A5A5A)
val Grey80 = Color(0xFF333333)
val Grey90 = Color(0xFF1A1A1A)
val Blue05 = Color(0xFFF0F6FF)
val Blue10 = Color(0xFFBCDAFF)
val Blue20 = Color(0xFF89BDFF)
val Blue30 = Color(0xFF5CA2FF)
val Blue40 = Color(0xFF358BFA)
val Blue50 = Color(0xFF1776F1)
val Blue60 = Color(0xFF0064E5)
val Blue70 = Color(0xFF0459C8)
val Blue80 = Color(0xFF094EA8)
val Blue90 = Color(0xFF0E4287)
val Red05 = Color(0xFFFFF3F3)
val Red10 = Color(0xFFFFC5C1)
val Red20 = Color(0xFFFF9690)
val Red30 = Color(0xFFFF6C64)
val Red40 = Color(0xFFFB493F)
val Red50 = Color(0xFFEB342A)
val Red60 = Color(0xFFD7261C)
val Red70 = Color(0xFFBF1C13)
val Red80 = Color(0xFFA4160E)
val Red90 = Color(0xFF87120C)
val Teal05 = Color(0xFFEAFBF8)
val Teal10 = Color(0xFFD6F8F2)
val Teal20 = Color(0xFFA6F0E3)
val Teal30 = Color(0xFF7CE4D3)
val Teal40 = Color(0xFF5BD5C1)
val Teal50 = Color(0xFF43C1AC)
val Teal60 = Color(0xFF31A894)
val Teal70 = Color(0xFF2D8879)
val Teal80 = Color(0xFF17695C)
val Teal90 = Color(0xFF08463B)
val Green05 = Color(0xFFE6F5E5)
val Green10 = Color(0xFFC5E6BF)
val Green20 = Color(0xFF9ED695)
val Green30 = Color(0xFF75C769)
val Green40 = Color(0xFF54BB47)
val Green50 = Color(0xFF2EAF1D)
val Green60 = Color(0xFF22A012)
val Green70 = Color(0xFF0E8E00)
val Green80 = Color(0xFF007D00)
val Green90 = Color(0xFF005F00)
val Yellow05 = Color(0xFFFFF9E7)
val Yellow10 = Color(0xFFFFF2CF)
val Yellow20 = Color(0xFFFFE19C)
val Yellow30 = Color(0xFFFFCF6F)
val Yellow40 = Color(0xFFFBBD48)
val Yellow50 = Color(0xFFEAA92C)
val Yellow60 = Color(0xFFD69518)
val Yellow70 = Color(0xFFBD800B)
val Yellow80 = Color(0xFF9F6A04)
val Yellow90 = Color(0xFF7E5400)

val Orange05 = Color(0xFFFFEBEB)
val Orange10 = Color(0xFFFED0CD)
val Orange20 = Color(0xFFFDB8B0)
val Orange30 = Color(0xFFFCA595)
val Orange40 = Color(0xFFFB947A)
val Orange50 = Color(0xFFF5692D)
val Orange60 = Color(0xFFEB4D1B)
val Orange70 = Color(0xFFDD2A0A)
val Orange80 = Color(0xFFB11002)
val Orange90 = Color(0xFF870000)

val Navy05 = Color(0xFFF2F7FF)
val Navy10 = Color(0xFFDBE7FF)
val Navy20 = Color(0xFFC3D7FF)
val Navy30 = Color(0xFFAEC9FF)
val Navy40 = Color(0xFF99BAFF)
val Navy50 = Color(0xFF729AFF)
val Navy60 = Color(0xFF5784F6)
val Navy70 = Color(0xFF4370DF)
val Navy80 = Color(0xFF2A4B95)
val Navy90 = Color(0xFF132342)

val BROWN = Color(0xFF613D09)
val Purple = Color(0xFF7F56D9)
val LightPurple: Color = Color(0x447F56D9)
val VeryLightPurplele: Color = Color(0x44AB8DEC)

@Immutable
data class DongsaniColors(
    val system: SystemColors = SystemColors(),
    val background: BackgroundColors = BackgroundColors(),
    val overlay: OverlayColors = OverlayColors(),
    val label: LabelColors = LabelColors(),
    val brand: BrandColors = BrandColors(),
    val separator: Color = Grey90.copy(alpha = .08f)
)

val LocalDongsaniDongsaniColors = staticCompositionLocalOf { DongsaniColors() }

@Immutable
data class BackgroundColors(
    val defaultBase: Color = Color.White,
    val defaultElevated: Color = Color.White,
    val groupedBase: Color = Grey05,
    val groupedUpperBase: Color = Color.White,
    val groupedElevated: Color = Color.White
)

@Immutable
data class LabelColors(
    val onBgPrimary: Color = Grey90,
    val onBgSecondary: Color = Grey90.copy(alpha = 0.57f),
    val onBgTertiary: Color = Grey90.copy(alpha = 0.29f),
    val onTintPrimary: Color = Grey05,
    val onTintSecondary: Color = Grey05.copy(alpha = 0.6f),
    val onTintTertiary: Color = Grey05.copy(alpha = 0.3f)
)

@Immutable
data class OverlayColors(
    val thick: Color = Grey80.copy(alpha = 0.9f),
    val basic: Color = Grey90.copy(alpha = 0.4f),
    val thin: Color = Grey70.copy(alpha = 0.05f),
    val thickDisabled: Color = Grey80.copy(alpha = 0.38f),
    val basicDisabled: Color = Grey90.copy(alpha = 0.17f),
    val thinDisabled: Color = Grey70.copy(alpha = 0.03f),
    val thinYellow: Color = Yellow50.copy(alpha = 0.12f),
    val thinRed: Color = Red70.copy(alpha = 0.08f),
    val thinBlue: Color = Blue60.copy(alpha = 0.08f)
)

@Immutable
data class SystemColors(
    val red: Color = Red70,
    val orange: Color = Orange50,
    val yellow: Color = Yellow50,
    val green: Color = Green70,
    val teal: Color = Teal70,
    val blue: Color = Blue60,
    val navy: Color = Navy90,
    val white: Color = Color.White,
    val black: Color = Color.Black,
    val inverse: Color = Color.Black,
    val grey: Color = Grey50,
    val grey2: Color = Grey40,
    val grey3: Color = Grey30,
    val grey4: Color = Grey20,
    val grey5: Color = Grey10,
    val grey6: Color = Grey05
)

@Immutable
data class BrandColors(
    val blueLight: Color = Color(0xFF57CAFF),
    val redLight: Color = Color(0xFFFF7DB1),
    val orangeLight: Color = Color(0xFFFBBD48),
    val yellowLight: Color = Color(0xFFFFD527),
    val greenLight: Color = Color(0xFF5BDE6E),
    val mintLight: Color = Color(0xFF41E1B9),
    val navyLight: Color = Color(0xFF89BDFF),
    val violetLight: Color = Color(0xFFCE82FF),
    val blueSoft: Color = Color(0xFFE2F0F3),
    val redSoft: Color = Color(0xFFF2E6E6),
    val orangeSoft: Color = Color(0xFFFAF0EB),
    val yellowSoft: Color = Color(0xFFFFFAEB),
    val greenSoft: Color = Color(0xFFEAF1EA),
    val mintSoft: Color = Color(0xFFE8F3F0),
    val navySoft: Color = Color(0xFFEBF0F5),
    val violetSoft: Color = Color(0xFFF5EFF6),
    val bluePastel: Color = Color(0xFF78A0E6),
    val redPastel: Color = Color(0xFFF58C82),
    val orangePastel: Color = Color(0xFFFA9B73),
    val yellowPastel: Color = Color(0xFFF9DD71),
    val greenPastel: Color = Color(0xFF69CA90),
    val mintPastel: Color = Color(0xFF91E6D7),
    val navyPastel: Color = Color(0xFF506482),
    val violetPastel: Color = Color(0xFFD1A1D1)
)

val DarkSystemColors = SystemColors(
    red = Red50,
    orange = Orange50,
    yellow = Yellow40,
    green = Green50,
    teal = Teal50,
    blue = Blue40,
    navy = Navy80,
    inverse = Color.White,
    grey = Grey40,
    grey2 = Grey50,
    grey3 = Grey60,
    grey4 = Grey70,
    grey5 = Grey80,
    grey6 = Grey90
)

val DarkBackgroundColors = BackgroundColors(
    defaultBase = Grey90,
    defaultElevated = Grey80,
    groupedBase = Color.Black,
    groupedUpperBase = Grey90,
    groupedElevated = Grey80
)

val DarkOverlayColors = OverlayColors(
    thick = Grey70.copy(alpha = 0.9f),
    basic = Grey90.copy(alpha = 0.6f),
    thin = Grey70.copy(alpha = 0.28f),
    thickDisabled = Grey70.copy(alpha = 0.37f),
    basicDisabled = Grey90.copy(alpha = 0.24f),
    thinDisabled = Grey70.copy(alpha = 0.11f),
    thinYellow = Yellow20.copy(alpha = 0.16f),
    thinRed = Red20.copy(alpha = 0.16f),
    thinBlue = Blue20.copy(alpha = 0.16f)
)

val DarkLabelColors = LabelColors(
    onBgPrimary = Grey05,
    onBgSecondary = Grey05.copy(alpha = 0.6f),
    onBgTertiary = Grey05.copy(alpha = 0.3f),
    onTintPrimary = Grey90,
    onTintSecondary = Grey90.copy(alpha = 0.57f),
    onTintTertiary = Grey90.copy(alpha = 0.29f)
)

val DarkDongsaniColors = DongsaniColors(
    system = DarkSystemColors,
    background = DarkBackgroundColors,
    overlay = DarkOverlayColors,
    label = DarkLabelColors,
    separator = Grey05.copy(alpha = .10f)
)
