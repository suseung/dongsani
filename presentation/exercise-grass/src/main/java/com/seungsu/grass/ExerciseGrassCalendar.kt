package com.seungsu.grass

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seungsu.design.ThemePreview
import com.seungsu.design.theme.DongsaniTheme
import com.seungsu.design.theme.Purple
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun ExerciseGrassCalendar(
    modifier: Modifier = Modifier,
    date: YearMonth,
    lengthOfMonth: Int,
    currentDayOfWeek: DayOfWeek,
    currentTotalExerciseTimes: Map<LocalDate, Long>,
    color: Color,
    onUiAction: (ExerciseGrassIntent) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CalendarHeader(
            modifier = Modifier
                .padding(
                    horizontal = 24.dp
                ),
            date = date,
            onUiAction = onUiAction
        )
        CalendarWeekOfDay(
            modifier = Modifier.padding(top = 16.dp)
        )
        CalendarDate(
            lengthOfMonth = lengthOfMonth,
            currentDayOfWeek = currentDayOfWeek,
            currentTotalExerciseTimes = currentTotalExerciseTimes,
            date = date,
            color = color,
            onClickGrass = { onUiAction(ExerciseGrassIntent.OnClickGrass(it)) }
        )
    }
}

@ThemePreview
@Composable
private fun ExerciseGrassCalendarPreview() {
    DongsaniTheme {
        ExerciseGrassCalendar(
            modifier = Modifier.padding(16.dp),
            date = YearMonth.now(),
            lengthOfMonth = 31,
            currentDayOfWeek = DayOfWeek.MONDAY,
            currentTotalExerciseTimes = mapOf(),
            color = Purple
        )
    }
}
