package com.seungsu.domain.repository

import com.seungsu.domain.model.ExerciseRecordItemEntity
import java.time.LocalDate
import java.time.YearMonth

interface ExerciseRecordRepository {
    suspend fun getCurrentDayExerciseRecords(date: LocalDate): List<ExerciseRecordItemEntity>
    suspend fun getExerciseRecordsWithYearMonth(yearMonth: YearMonth): List<ExerciseRecordItemEntity>
    suspend fun insertExerciseRecords(recordItemEntity: ExerciseRecordItemEntity)
}
