package com.example.sparring.makeprofile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.component.DongsaniOutlineButton
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun SelectItem(
    modifier: Modifier = Modifier,
    content: String,
    isSelected: Boolean,
    onClickItem: () -> Unit = {}
) {
    DongsaniOutlineButton(
        modifier = modifier,
        content = content,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected.not()) Color.Transparent else DongsaniTheme.colors.overlay.thick.copy(alpha = 0.3f),
            contentColor = DongsaniTheme.colors.label.onBgPrimary
        ),
        onClick = onClickItem
    )
}

@ThemePreview
@Composable
private fun SkillPreview() {
    DongsaniTheme {
        Column {
            SelectItem(
                modifier = Modifier.padding(16.dp),
                content = "All Rounder",
                isSelected = false
            )
            SelectItem(
                modifier = Modifier.padding(16.dp),
                content = "All Rounder",
                isSelected = true
            )
        }
    }
}
