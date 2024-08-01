package com.seungsu.data.repository.ExerciseRecordRepository

import com.seungsu.data.database.dao.ExerciseRecordDao
import com.seungsu.data.model.local.ExerciseRecordDbEntity
import com.seungsu.data.model.local.toDomain
import com.seungsu.data.model.local.toLocalEntity
import com.seungsu.data.repository.ExerciseRecordRepository.remote.ExerciseRecordLocalDataSource
import com.seungsu.domain.model.ExerciseRecordItemEntity
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

internal class ExerciseRecordLocalDataSourceImpl @Inject constructor(
    private val exerciseRecordDao: ExerciseRecordDao
): ExerciseRecordLocalDataSource {

    override suspend fun getCurrentDayExerciseRecords(date: LocalDate): List<ExerciseRecordItemEntity> {
        return exerciseRecordDao.getCurrentDayExerciseRecords(date.toEpochDay()).map(ExerciseRecordDbEntity::toDomain)
    }

    override suspend fun getExerciseRecordsWithYearMonth(yearMonth: YearMonth): List<ExerciseRecordItemEntity> {
        val startDate = LocalDate.of(yearMonth.year, yearMonth.month, 1)
        val endDate = LocalDate.of(yearMonth.year, yearMonth.month, yearMonth.lengthOfMonth())
        return exerciseRecordDao.getExerciseRecordsWithYearMonth(startDate.toEpochDay(), endDate.toEpochDay()).map(ExerciseRecordDbEntity::toDomain)
    }

    override suspend fun insertExerciseRecord(recordItemEntity: ExerciseRecordItemEntity) {
        return exerciseRecordDao.insertExerciseRecords(recordItemEntity.toLocalEntity())
    }
}
