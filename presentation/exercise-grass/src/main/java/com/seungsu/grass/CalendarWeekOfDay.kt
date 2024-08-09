package com.seungsu.grass

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungsu.common.WEEK_OF_DAYS
import com.seungsu.design.theme.DongsaniTheme

@Composable
fun CalendarWeekOfDay(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        WEEK_OF_DAYS.forEach {
            Text(
                modifier = Modifier
                    .padding(end = 14.dp)
                    .size(30.dp),
                text = it,
                style = DongsaniTheme.typos.regular.font14,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun CalendarWeekOfDayPreview() {
    DongsaniTheme {
        CalendarWeekOfDay()
    }
}
