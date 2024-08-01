package com.seungsu.data.repository.ExerciseRecordRepository

import com.seungsu.domain.model.ExerciseRecordItemEntity
import com.seungsu.domain.repository.ExerciseRecordRepository
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

internal class ExerciseRecordRepositoryImpl @Inject constructor(
    private val exerciseRecordLocalDataSource: ExerciseRecordLocalDataSourceImpl
): ExerciseRecordRepository {

    override suspend fun getCurrentDayExerciseRecords(date: LocalDate): List<ExerciseRecordItemEntity> {
        return exerciseRecordLocalDataSource.getCurrentDayExerciseRecords(date)
    }

    override suspend fun getExerciseRecordsWithYearMonth(yearMonth: YearMonth): List<ExerciseRecordItemEntity> {
        return exerciseRecordLocalDataSource.getExerciseRecordsWithYearMonth(yearMonth)
    }

    override suspend fun insertExerciseRecords(recordItemEntity: ExerciseRecordItemEntity) {
        return exerciseRecordLocalDataSource.insertExerciseRecord(recordItemEntity)
    }
}
