package com.seungsu.domain.usecase

import com.seungsu.domain.base.FlowUseCase
import com.seungsu.domain.model.ExerciseRecordItemEntity
import com.seungsu.domain.repository.ExerciseRecordRepository
import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentDayExerciseRecordUseCase @Inject constructor(
    private val exerciseRecordRepository: ExerciseRecordRepository
): FlowUseCase<LocalDate, List<ExerciseRecordItemEntity>>() {

    override fun execute(params: LocalDate): Flow<List<ExerciseRecordItemEntity>> = flow {
        emit(exerciseRecordRepository.getCurrentDayExerciseRecords(params))
    }
}
