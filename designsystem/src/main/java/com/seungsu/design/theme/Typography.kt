package com.seungsu.design.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.design.R
import com.seungsu.design.ThemePreview

private val PretendardFontFamily = FontFamily(
    Font(R.font.font_regular, FontWeight.Normal)
)

private val PretendardBoldStyle = TextStyle(
    fontFamily = PretendardFontFamily,
    fontWeight = FontWeight.W600,
    letterSpacing = (-0.2).sp
)

private val PretendardRegularStyle = TextStyle(
    fontFamily = PretendardFontFamily,
    fontWeight = FontWeight.W400,
    letterSpacing = (-0.2).sp
)

@Immutable
data class DongsaniTypography(
    val bold: BoldTextStyle = BoldTextStyle(),
    val regular: RegularTextStyle = RegularTextStyle()
)

val LocalDongsaniTypography = staticCompositionLocalOf { DongsaniTypography() }

@Immutable
data class BoldTextStyle(
    val font48: TextStyle = PretendardBoldStyle.copy(
        fontSize = 48.sp,
        lineHeight = 64.sp
    ),
    val font36: TextStyle = PretendardBoldStyle.copy(
        fontSize = 36.sp,
        lineHeight = 48.sp
    ),
    val font32: TextStyle = PretendardBoldStyle.copy(
        fontSize = 32.sp,
        lineHeight = 44.sp
    ),
    val font28: TextStyle = PretendardBoldStyle.copy(
        fontSize = 28.sp,
        lineHeight = 38.sp
    ),
    val font24: TextStyle = PretendardBoldStyle.copy(
        fontSize = 24.sp,
        lineHeight = 34.sp
    ),
    val font18: TextStyle = PretendardBoldStyle.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    val font16: TextStyle = PretendardBoldStyle.copy(
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    val font14: TextStyle = PretendardBoldStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    val font12: TextStyle = PretendardBoldStyle.copy(
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    val font11: TextStyle = PretendardBoldStyle.copy(
        fontSize = 11.sp,
        lineHeight = 18.sp
    )
)

@Immutable
data class RegularTextStyle(
    val font48: TextStyle = PretendardRegularStyle.copy(
        fontSize = 48.sp,
        lineHeight = 64.sp
    ),
    val font36: TextStyle = PretendardRegularStyle.copy(
        fontSize = 36.sp,
        lineHeight = 48.sp
    ),
    val font32: TextStyle = PretendardRegularStyle.copy(
        fontSize = 32.sp,
        lineHeight = 44.sp
    ),
    val font28: TextStyle = PretendardRegularStyle.copy(
        fontSize = 28.sp,
        lineHeight = 38.sp
    ),
    val font24: TextStyle = PretendardRegularStyle.copy(
        fontSize = 24.sp,
        lineHeight = 34.sp
    ),
    val font18: TextStyle = PretendardRegularStyle.copy(
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    val font16: TextStyle = PretendardRegularStyle.copy(
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    val font14: TextStyle = PretendardRegularStyle.copy(
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    val font12: TextStyle = PretendardRegularStyle.copy(
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    val font11: TextStyle = PretendardRegularStyle.copy(
        fontSize = 11.sp,
        lineHeight = 18.sp
    )
)

@ThemePreview
@Composable
private fun BoldTypographyPreview() {
    val text = "Bold Typography"
    val fontLists = listOf(
        BoldTextStyle().font48,
        BoldTextStyle().font36,
        BoldTextStyle().font32,
        BoldTextStyle().font28,
        BoldTextStyle().font24,
        BoldTextStyle().font18,
        BoldTextStyle().font16,
        BoldTextStyle().font14,
        BoldTextStyle().font12,
        BoldTextStyle().font11
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(fontLists.size) {
            Text(
                text = text,
                style = fontLists[it]
            )
        }
    }
}

@ThemePreview
@Composable
private fun RegularTypographyPreview() {
    val text = "Regular Typography"
    val fontLists = listOf(
        RegularTextStyle().font48,
        RegularTextStyle().font36,
        RegularTextStyle().font32,
        RegularTextStyle().font28,
        RegularTextStyle().font24,
        RegularTextStyle().font18,
        RegularTextStyle().font16,
        RegularTextStyle().font14,
        RegularTextStyle().font12,
        RegularTextStyle().font11
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(fontLists.size) {
            Text(
                text = text,
                style = fontLists[it]
            )
        }
    }
}
