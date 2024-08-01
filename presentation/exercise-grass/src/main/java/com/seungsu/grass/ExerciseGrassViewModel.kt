package com.seungsu.grass

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seungsu.core.base.MVIViewModel
import com.seungsu.domain.base.ApiResult
import com.seungsu.domain.base.asResult
import com.seungsu.domain.model.ExerciseRecordItemEntity
import com.seungsu.domain.usecase.GetExerciseRecordsWithYearMonthUseCase
import com.seungsu.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.YearMonth
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@HiltViewModel
class ExerciseGrassViewModel @Inject constructor(
    private val getExerciseRecordsWithYearMonthUseCase: GetExerciseRecordsWithYearMonthUseCase,
    savedStateHandle: SavedStateHandle,
) : MVIViewModel<ExerciseGrassIntent, ExerciseGrassState, ExerciseGrassEffect>() {

    private val currentYearMonth = savedStateHandle.get<YearMonth>(KEY_CURRENT_YEAR_MONTH) ?: YearMonth.now()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val exerciseGrassResult = loadDataSignal
        .flatMapLatest { getExerciseRecordsWithYearMonthUseCase(currentYearMonth).asResult() }
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading)

    init {
        viewModelScope.launch {
            exerciseGrassResult.collect { apiResult ->
                setState {
                    when (apiResult) {
                        is ApiResult.Success -> {
                            val data = apiResult.data.map(ExerciseRecordItemEntity::toUiModel)
                            ExerciseGrassState.Success(
                                monthOffset = 0,
                                currentRecordLists = data
                            )
                        }
                        else -> ExerciseGrassState.Loading
                    }
                }
            }
        }
    }

    override fun createInitialState() = ExerciseGrassState.Loading

    override suspend fun processIntent(intent: ExerciseGrassIntent) {
        when (intent) {
            ExerciseGrassIntent.OnClickMinusMonth -> processChangeMonth(-1)
            ExerciseGrassIntent.OnClickPlusMonth -> processChangeMonth(+1)
            is ExerciseGrassIntent.OnClickGrass -> processOnClickGrass(intent.date)
        }
    }

    private fun processChangeMonth(offset: Int) = currentStateIf<ExerciseGrassState.Success> {
        val newYearOfMonth = YearMonth.now().plusMonths(monthOffset + offset.toLong())
        viewModelScope.launch {
            getExerciseRecordsWithYearMonthUseCase(newYearOfMonth)
                .asResult()
                .collect { apiResult ->
                    when (apiResult) {
                        is ApiResult.Loading -> Unit
                        is ApiResult.Error -> Unit
                        is ApiResult.Success -> {
                            val data = apiResult.data.map(ExerciseRecordItemEntity::toUiModel)
                            setState {
                                copy(
                                    monthOffset = monthOffset + offset,
                                    currentRecordLists = data
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun processOnClickGrass(date: LocalDate) = currentStateIf<ExerciseGrassState.Success> {
        setState { copy(currentSelectedDate = date) }
    }

    companion object {
        private const val KEY_CURRENT_YEAR_MONTH = "current_year_month"
    }
}
