package com.seungsu.design.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun DongsaniOutlineButton(
    modifier: Modifier = Modifier,
    content: String,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Color.Transparent,
        contentColor = DongsaniTheme.colors.label.onBgPrimary
    ),
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = colors,
        border = BorderStroke(
            width = 1.dp,
            color = DongsaniTheme.colors.label.onBgTertiary
        ),
        contentPadding = PaddingValues(
            vertical = 6.dp,
            horizontal = 12.dp
        )
    ) {
        Text(
            text = content,
            style = DongsaniTheme.typos.regular.font14,
            color = DongsaniTheme.colors.label.onBgPrimary
        )
    }
}

@ThemePreview
@Composable
fun DongsaniOutlineButtonPreview() {
    DongsaniOutlineButton(
        modifier = Modifier.padding(16.dp),
        content = "White",
        onClick = {}
    )
}
