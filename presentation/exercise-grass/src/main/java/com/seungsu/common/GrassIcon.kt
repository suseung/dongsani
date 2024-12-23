package com.seungsu.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.Purple

@Composable
fun GrassIcon(
    modifier: Modifier = Modifier,
    color: Color
) {
    Text(
        modifier = modifier
            .padding(end = 14.dp)
            .size(30.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = color),
        text = ""
    )
}

@ThemePreview
@Composable
private fun GrassIconPreview() {
    GrassIcon(
        color = Purple,
        modifier = Modifier.padding(16.dp)
    )
}
