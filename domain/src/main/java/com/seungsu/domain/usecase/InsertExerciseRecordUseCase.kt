package com.seungsu.domain.usecase

import com.seungsu.domain.base.FlowUseCase
import com.seungsu.domain.model.ExerciseRecordItemEntity
import com.seungsu.domain.repository.ExerciseRecordRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class InsertExerciseRecordUseCase @Inject constructor(
    private val exerciseRecordRepository: ExerciseRecordRepository
): FlowUseCase<ExerciseRecordItemEntity, Unit>() {

    override fun execute(params: ExerciseRecordItemEntity): Flow<Unit> = flow {
        emit(exerciseRecordRepository.insertExerciseRecords(params))
    }
}
