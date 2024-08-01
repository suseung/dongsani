package com.seungsu.grass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seungsu.common.GrassIcon
import com.seungsu.design.theme.DongsaniTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarDate(
    modifier: Modifier = Modifier,
    currentDayOfWeek: DayOfWeek,
    lengthOfMonth: Int,
    currentTotalExerciseTimes: Map<LocalDate, Long>,
    date: YearMonth,
    color: Color = DongsaniTheme.color.Purple,
    onClickGrass: (LocalDate) -> Unit = {}
) {
    repeat(5) { row ->
        Row(
            modifier = modifier.padding(bottom = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(7) { col ->
                val itemIndex = (row * 7) + col - (currentDayOfWeek.value % 7)
                val currentDate = if (itemIndex in 0..lengthOfMonth - 1) {
                    LocalDate.of(date.year, date.month, itemIndex + 1)
                } else {
                    null
                }
                GrassIcon(
                    modifier = Modifier
                        .clickable {
                            itemIndex.takeIf { it >= 0 }?.let {
                                currentDate?.let { onClickGrass(it) }
                            }
                        },
                    color = when {
                        itemIndex in 0..<lengthOfMonth -> {
                            val exerciseTime = currentTotalExerciseTimes.getOrDefault(currentDate, -1L)
                            when (exerciseTime) {
                                in 1L..<10L -> color.copy(alpha = 0.1f)
                                in 10L..20L -> color.copy(alpha = 0.3f)
                                in 20L..30L -> color.copy(alpha = 0.5f)
                                in 30L..<40L -> color.copy(alpha = 0.7f)
                                in 40L..Int.MAX_VALUE -> color.copy(alpha = 0.9f)
                                else -> color.copy(alpha = 0f) // 투멍
                            }
                        }
                        else -> Color.Transparent
                    }
                )
            }
        }
    }
}

@Preview(backgroundColor = 0xffffff, showBackground = true)
@Composable
fun CalendarDatePreview() {
    DongsaniTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            CalendarDate(
                lengthOfMonth = 31,
                currentDayOfWeek = DayOfWeek.TUESDAY,
                currentTotalExerciseTimes = mapOf(),
                date = YearMonth.now(),
                color = DongsaniTheme.color.Purple
            )
        }
    }
}
