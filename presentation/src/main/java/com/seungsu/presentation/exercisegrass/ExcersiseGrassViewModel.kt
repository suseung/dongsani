package com.seungsu.presentation.exercisegrass

import androidx.lifecycle.viewModelScope
import com.seungsu.presentation.base.MVIViewModel
import com.seungsu.presentation.exercisegrass.model.ExerciseRecordItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class ExcersiseGrassViewModel @Inject constructor(
) : MVIViewModel<ExerciseGrassIntent, ExerciseGrassState, ExerciseGrassEffect>() {

    private var timerJob: Job? = null
    override fun createInitialState() = ExerciseGrassState.Success()

    override suspend fun processIntent(intent: ExerciseGrassIntent) = when (intent) {
        ExerciseGrassIntent.OnClickStart -> processStartTimer()
        ExerciseGrassIntent.OnClickStop -> processStopTimer()
        ExerciseGrassIntent.OnResetTimer -> processResetTimer()

        is ExerciseGrassIntent.OnSaveMemo -> processOnSaveMemo(intent.memo)
    }

    private fun processStartTimer() = currentStateIf<ExerciseGrassState.Success> {
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
                currentStateIf<ExerciseGrassState.Success> {
                    setState {
                        copy(currentTime = currentTime + 1)
                    }
                }
            }
        }
    }

    private fun processStopTimer() = currentStateIf<ExerciseGrassState.Success> {
        timerJob?.cancel()
        setState { copy(isStart = false) }
        setEffect(ExerciseGrassEffect.ShowMemoBottomSheet)
    }

    private fun processResetTimer() = currentStateIf<ExerciseGrassState.Success> {
        setState { copy(currentTime = 0L) }
    }

    private fun processOnSaveMemo(memo: String) = currentStateIf<ExerciseGrassState.Success> {
        setState {
            copy(
                recordItems = recordItems + ExerciseRecordItem(memo = memo, recordTime = currentTime),
                currentTime = 0L
            )
        }
        // Todo DB에 저장
    }
}
