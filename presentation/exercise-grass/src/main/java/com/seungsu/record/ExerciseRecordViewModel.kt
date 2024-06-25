package com.seungsu.record

import androidx.lifecycle.viewModelScope
import com.seungsu.core.base.MVIViewModel
import com.seungsu.model.ExerciseRecordItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class ExerciseRecordViewModel @Inject constructor(
) : MVIViewModel<ExerciseRecordIntent, ExerciseRecordState, ExerciseRecordEffect>() {

    private var timerJob: Job? = null
    override fun createInitialState() = ExerciseRecordState.Success()

    override suspend fun processIntent(intent: ExerciseRecordIntent) = when (intent) {
        ExerciseRecordIntent.OnClickStart -> processStartTimer()
        ExerciseRecordIntent.OnClickStop -> processStopTimer()
        ExerciseRecordIntent.OnResetTimer -> processResetTimer()

        is ExerciseRecordIntent.OnSaveMemo -> processOnSaveMemo(intent.memo)
    }

    private fun processStartTimer() = currentStateIf<ExerciseRecordState.Success> {
        timerJob?.cancel()
        setState {
            copy(
                isStart = true,
                currentTime = 0L
            )
        }
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                currentStateIf<ExerciseRecordState.Success> {
                    setState {
                        copy(currentTime = currentTime + 1)
                    }
                }
            }
        }
    }

    private fun processStopTimer() = currentStateIf<ExerciseRecordState.Success> {
        timerJob?.cancel()
        setState { copy(isStart = false) }
        setEffect(ExerciseRecordEffect.ShowMemoBottomSheet)
    }

    private fun processResetTimer() = currentStateIf<ExerciseRecordState.Success> {
        setState { copy(currentTime = 0L) }
    }

    private fun processOnSaveMemo(memo: String) = currentStateIf<ExerciseRecordState.Success> {
        setState {
            copy(
                recordItems = recordItems + ExerciseRecordItem(memo = memo, recordTime = currentTime),
                currentTime = 0L
            )
        }
        // Todo DB에 저장
    }
}
