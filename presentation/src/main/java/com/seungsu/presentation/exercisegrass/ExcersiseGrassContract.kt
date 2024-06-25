package com.seungsu.presentation.exercisegrass

import com.seungsu.presentation.HOUR_TO_SEC
import com.seungsu.presentation.MIN_TO_SEC
import com.seungsu.presentation.base.ViewEffect
import com.seungsu.presentation.base.ViewIntent
import com.seungsu.presentation.base.ViewState
import com.seungsu.presentation.exercisegrass.model.ExerciseRecordItem

sealed interface ExerciseGrassIntent : ViewIntent {
    data object OnClickStart: ExerciseGrassIntent
    data object OnClickStop: ExerciseGrassIntent
    data object OnResetTimer: ExerciseGrassIntent

    @JvmInline
    value class OnSaveMemo(val memo: String): ExerciseGrassIntent
}

sealed interface ExerciseGrassState : ViewState {
    data object Loading: ExerciseGrassState
    data class Success(
        val currentTime: Long = 0L,
        val isStart: Boolean = false,
        val recordItems: List<ExerciseRecordItem> = emptyList()
    ): ExerciseGrassState {
        val parsedCurrentTime: String
            get() {
                val hour = currentTime / HOUR_TO_SEC
                val min = (currentTime / MIN_TO_SEC) % MIN_TO_SEC
                val sec = currentTime % MIN_TO_SEC
                return String.format("%02d : %02d : %02d", hour, min, sec)
            }
    }
}


sealed interface ExerciseGrassEffect : ViewEffect {
    data object ShowMemoBottomSheet: ExerciseGrassEffect
}
