package com.seungsu.data.repository.ExerciseRecordRepository.remote

import com.seungsu.domain.model.ExerciseRecordItemEntity
import java.time.LocalDate
import java.time.YearMonth

interface ExerciseRecordLocalDataSource {
    suspend fun getCurrentDayExerciseRecords(date: LocalDate): List<ExerciseRecordItemEntity>
    suspend fun getExerciseRecordsWithYearMonth(yearMonth: YearMonth): List<ExerciseRecordItemEntity>
    suspend fun insertExerciseRecord(recordItemEntity: ExerciseRecordItemEntity)
}
