package com.seungsu.record

import com.seungsu.common.HOUR_TO_SEC
import com.seungsu.common.MIN_TO_SEC
import com.seungsu.common.model.ContentsType
import com.seungsu.common.base.ViewEffect
import com.seungsu.common.base.ViewIntent
import com.seungsu.common.base.ViewState
import com.seungsu.model.ExerciseRecordItem

sealed interface ExerciseRecordIntent : ViewIntent {
    data object OnClickStart: ExerciseRecordIntent
    data object OnClickStop: ExerciseRecordIntent
    data object OnResetTimer: ExerciseRecordIntent

    @JvmInline
    value class OnSaveMemo(val memo: String): ExerciseRecordIntent

    @JvmInline
    value class OnChangeContent(val content: ContentsType): ExerciseRecordIntent
}

sealed interface ExerciseRecordState : ViewState {
    data object Loading: ExerciseRecordState
    data class Success(
        val currentTime: Long = 0L,
        val isStart: Boolean = false,
        val recordItems: List<ExerciseRecordItem> = emptyList()
    ): ExerciseRecordState {
        val parsedCurrentTime: String
            get() {
                val hour = currentTime / HOUR_TO_SEC
                val min = (currentTime / MIN_TO_SEC) % MIN_TO_SEC
                val sec = currentTime % MIN_TO_SEC
                return String.format("%02d : %02d : %02d", hour, min, sec)
            }
    }
}


sealed interface ExerciseRecordEffect : ViewEffect {
    data object ShowMemoBottomSheet: ExerciseRecordEffect
    data object ShowRestartDialog: ExerciseRecordEffect
}
