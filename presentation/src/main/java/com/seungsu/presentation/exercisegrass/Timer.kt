package com.seungsu.presentation.exercisegrass

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seungsu.presentation.theme.DongsaniTheme

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    time: String
) {
    Box(
        modifier = modifier
            .padding(32.dp)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = DongsaniTheme.color.DarkGray
            )
            .size(255.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    shape = CircleShape,
                    color = DongsaniTheme.color.DarkGray
                )
                .size(240.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = time,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp,
                    color = DongsaniTheme.color.Black
                )
            )
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun TimerPreview() {
    DongsaniTheme {
        Timer(time = "12:34:55")
    }
}
