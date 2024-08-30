package com.seungsu.grass

import androidx.compose.ui.graphics.Color
import com.seungsu.common.base.ViewEffect
import com.seungsu.common.base.ViewIntent
import com.seungsu.common.base.ViewState
import com.seungsu.model.ExerciseRecordItem
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

sealed interface ExerciseGrassIntent : ViewIntent {
    data object OnClickMinusMonth : ExerciseGrassIntent
    data object OnClickPlusMonth : ExerciseGrassIntent

    @JvmInline
    value class OnClickGrass(val date: LocalDate): ExerciseGrassIntent
}

sealed interface ExerciseGrassState : ViewState {
    data object Loading : ExerciseGrassState
    data class Success(
        val currentRecordLists: List<ExerciseRecordItem> = listOf(),
        val monthOffset: Int = 0,
        val currentSelectedDate: LocalDate = LocalDate.now(),
        val color: Color = Color.Gray
    ) : ExerciseGrassState {
        val currentYearMonth: YearMonth
            get() = YearMonth.now().plusMonths(monthOffset.toLong())

        val currentLengthOfMonth: Int
            get() = currentYearMonth.lengthOfMonth()

        val currentDayOfWeek: DayOfWeek
            get() = LocalDate.of(currentYearMonth.year, currentYearMonth.month, 1).dayOfWeek

        val currentSelectedExerciseRecords: List<ExerciseRecordItem>
            get() = currentRecordLists.filter { it.recordDate == currentSelectedDate }

        val currentTotalExerciseTimes: Map<LocalDate, Long>
            get() = currentRecordLists.groupBy { it.recordDate }.mapValues { item ->
                item.value.sumOf { it.recordTime }
            }
    }
}

sealed interface ExerciseGrassEffect : ViewEffect {

}
