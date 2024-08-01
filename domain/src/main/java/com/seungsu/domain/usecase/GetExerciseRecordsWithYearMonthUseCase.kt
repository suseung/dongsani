package com.seungsu.domain.usecase

import com.seungsu.domain.base.FlowUseCase
import com.seungsu.domain.model.ExerciseRecordItemEntity
import com.seungsu.domain.repository.ExerciseRecordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.YearMonth
import javax.inject.Inject

class GetExerciseRecordsWithYearMonthUseCase @Inject constructor(
    private val exerciseRecordRepository: ExerciseRecordRepository
): FlowUseCase<YearMonth, List<ExerciseRecordItemEntity>>() {

    override fun execute(params: YearMonth): Flow<List<ExerciseRecordItemEntity>> = flow {
        emit(exerciseRecordRepository.getExerciseRecordsWithYearMonth(params))
    }
}
