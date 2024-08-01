package com.seungsu.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.seungsu.data.model.local.ExerciseRecordDbEntity

@Dao
internal interface ExerciseRecordDao {

    @Query("SELECT * FROM exerciseRecord WHERE recordDate == :targetEpochDay")
    suspend fun getCurrentDayExerciseRecords(targetEpochDay: Long): List<ExerciseRecordDbEntity>

    @Query("SELECT * FROM exerciseRecord WHERE recordDate BETWEEN :startTargetEpochDay AND :endTargetEpochDay")
    suspend fun getExerciseRecordsWithYearMonth(startTargetEpochDay: Long, endTargetEpochDay: Long): List<ExerciseRecordDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExerciseRecords(item: ExerciseRecordDbEntity)
}
