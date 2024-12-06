package com.seungsu.design.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun VerticalSeparator(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    height: Dp = 10.dp,
    color: Color = DongsaniTheme.colors.separator
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    Box(
        modifier
            .width(targetThickness)
            .height(height)
            .background(color = color)
    )
}

@ThemePreview
@Composable
private fun VerticalSeparatorPreview() {
    DongsaniTheme {
        Row(
            modifier = Modifier.width(100.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            VerticalSeparator(color = DongsaniTheme.colors.label.onBgPrimary)
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
