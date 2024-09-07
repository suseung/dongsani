package com.seungsu.record

import androidx.lifecycle.viewModelScope
import com.seungsu.common.base.MVIViewModel
import com.seungsu.common.model.ContentsType
import com.seungsu.domain.base.ApiResult
import com.seungsu.domain.base.asResult
import com.seungsu.domain.model.ExerciseRecordItemEntity
import com.seungsu.domain.usecase.GetCurrentDayExerciseRecordUseCase
import com.seungsu.domain.usecase.InsertExerciseRecordUseCase
import com.seungsu.domain.usecase.UpdateCurrentContentUseCase
import com.seungsu.model.ExerciseRecordItem
import com.seungsu.model.toDomainModel
import com.seungsu.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ExerciseRecordViewModel @Inject constructor(
    private val getCurrentDayExerciseRecordUseCase: GetCurrentDayExerciseRecordUseCase,
    private val insertExerciseRecordUseCase: InsertExerciseRecordUseCase,
    private val updateCurrentContentUseCase: UpdateCurrentContentUseCase
) : MVIViewModel<ExerciseRecordIntent, ExerciseRecordState, ExerciseRecordEffect>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val exerciseRecordResult = loadDataSignal
        .flatMapLatest {
            getCurrentDayExerciseRecordUseCase(LocalDate.now()).asResult()
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading)

    init {
        launch {
            initializeMock()
            exerciseRecordResult.collect { apiResult ->
                setState {
                    when (apiResult) {
                        is ApiResult.Success -> ExerciseRecordState.Success(
                            recordItems = apiResult.data.map(ExerciseRecordItemEntity::toUiModel)
                        )

                        else -> ExerciseRecordState.Loading
                    }
                }
            }
        }
    }

    override fun createInitialState() = ExerciseRecordState.Loading

    override suspend fun processIntent(intent: ExerciseRecordIntent) = when (intent) {
        ExerciseRecordIntent.OnClickStart -> processStartTimer()
        ExerciseRecordIntent.OnClickStop -> processStopTimer()
        ExerciseRecordIntent.OnResetTimer -> processResetTimer()

        is ExerciseRecordIntent.OnSaveMemo -> processOnSaveMemo(intent.memo)
        is ExerciseRecordIntent.OnChangeContent -> processOnChangeContent(intent.content)
    }

    private fun initializeMock() {
        launch {
            val lists = listOf(
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 8L,
                    recordDate = LocalDate.of(2024, 7, 1)
                ),
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 8L,
                    recordDate = LocalDate.of(2024, 7, 2)
                ),
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 10L,
                    recordDate = LocalDate.of(2024, 7, 3)
                ),
                ExerciseRecordItem(
                    memo = "중량팔굽혀펴기",
                    recordTime = 10L,
                    recordDate = LocalDate.of(2024, 7, 4)
                ),
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 70L,
                    recordDate = LocalDate.of(2024, 7, 4)
                ),
                ExerciseRecordItem(
                    memo = "턱걸이",
                    recordTime = 30L,
                    recordDate = LocalDate.of(2024, 7, 7)
                ),
                ExerciseRecordItem(
                    memo = "달리기",
                    recordTime = 8L,
                    recordDate = LocalDate.of(2024, 7, 7)
                ),
                ExerciseRecordItem(
                    memo = "윗몸일으키기",
                    recordTime = 10L,
                    recordDate = LocalDate.of(2024, 7, 8)
                ),
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 20L,
                    recordDate = LocalDate.of(2024, 7, 9)
                ),
                ExerciseRecordItem(
                    memo = "턱걸이",
                    recordTime = 2L,
                    recordDate = LocalDate.of(2024, 7, 9)
                ),
                ExerciseRecordItem(
                    memo = "주짓수",
                    recordTime = 58L,
                    recordDate = LocalDate.of(2024, 7, 14)
                ),
                ExerciseRecordItem(
                    memo = "배밀기",
                    recordTime = 10L,
                    recordDate = LocalDate.of(2024, 7, 17)
                ),
                ExerciseRecordItem(
                    memo = "턱걸이",
                    recordTime = 10L,
                    recordDate = LocalDate.of(2024, 7, 18)
                ),
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 20L,
                    recordDate = LocalDate.of(2024, 7, 19)
                ),
                ExerciseRecordItem(
                    memo = "팔굽혀펴기",
                    recordTime = 150L,
                    recordDate = LocalDate.of(2024, 7, 20)
                ),
            )
            lists.forEach {
                println(it)
                insertExerciseRecordUseCase(it.toDomainModel()).asResult()
                    .collect { apiResult ->
                        when (apiResult) {
                            is ApiResult.Success -> {
                                println("success")
                            }

                            is ApiResult.Loading -> {
                                println("loading")
                            }

                            is ApiResult.Error -> {
                                println("error")
                            }
                        }

                    }
            }
        }
    }

    private fun processStartTimer() = currentStateIf<ExerciseRecordState.Success> {
        setState {
            copy(
                isStart = true,
                currentTime = 0L
            )
        }
        launchLatest("timer") {
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
        cancelJob("timer")
        setState { copy(isStart = false) }
        setEffect(ExerciseRecordEffect.ShowMemoBottomSheet)
    }

    private fun processResetTimer() = currentStateIf<ExerciseRecordState.Success> {
        setState { copy(currentTime = 0L) }
    }

    private fun processOnSaveMemo(memo: String) = currentStateIf<ExerciseRecordState.Success> {
        val insertItem = ExerciseRecordItem(
            memo = memo,
            recordTime = currentTime,
            recordDate = LocalDate.now()
        )
        launch {
            insertExerciseRecordUseCase(insertItem.toDomainModel())
                .asResult()
                .collect { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            setState {
                                copy(
                                    recordItems = recordItems + insertItem,
                                    currentTime = 0L
                                )
                            }
                        }

                        else -> Unit
                    }
                }
        }
    }

    private fun processOnChangeContent(contentType: ContentsType) {
        launch {
            updateCurrentContentUseCase(contentType.code)
            setEffect(ExerciseRecordEffect.ShowRestartDialog)
        }
    }
}
