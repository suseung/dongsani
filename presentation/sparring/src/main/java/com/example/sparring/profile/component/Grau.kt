package com.example.sparring.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun Grau(
    modifier: Modifier = Modifier,
    isBlack: Boolean = false,
    repeatSize: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .width(120.dp)
            .background(
                color = if (isBlack.not()) DongsaniTheme.colors.system.black else DongsaniTheme.colors.system.red
            )
            .padding(horizontal = 20.dp)
    ) {
        if (repeatSize == 0) {
            Text(text = "")
        } else {
            repeat(repeatSize) {
                Text(
                    text = "",
                    modifier = Modifier
                        .width(10.dp)
                        .background(
                            color = DongsaniTheme.colors.system.white
                        )
                )
            }
        }
    }
}

@ThemePreview
@Composable
private fun GrauPreview() {
    DongsaniTheme {
        Column {
            Grau(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = DongsaniTheme.colors.system.blue
                    ),
                repeatSize = 0,
                isBlack = false
            )
            Grau(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = DongsaniTheme.colors.system.blue
                    ),
                repeatSize = 3,
                isBlack = false
            )
        }
    }
}
