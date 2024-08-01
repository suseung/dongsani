package com.seungsu.model

import androidx.compose.ui.graphics.Color
import com.seungsu.design.theme.DongsaniTheme

data class GrassColor(
    val color: String,
    val text: String
)

val GrassColors =
    listOf(
        GrassColor(
            color = "#B01111",
            text = "빨강"
        ),
        GrassColor(
            color = "#084F36",
            text = "초록"
        ),
        GrassColor(
            color = "#30078B",
            text = "파랑"
        ),
        GrassColor(
            color = "#7F56D9",
            text = "보라"
        ),
        GrassColor(
            color = "#F6C721",
            text = "노랑"
        )
    )