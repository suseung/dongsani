package com.seungsu.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.seungsu.domain.model.ExerciseRecordItemEntity
import java.time.LocalDate

@Entity(tableName = "exerciseRecord")
internal data class ExerciseRecordDbEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "memo") val memo: String,
    @ColumnInfo(name = "recordTime") val recordTime: Long,
    @ColumnInfo(name = "recordDate") val recordDate: Long
)

internal fun ExerciseRecordDbEntity.toDomain(): ExerciseRecordItemEntity {
    return ExerciseRecordItemEntity(
        memo = memo,
        recordTime = recordTime,
        recordDate = LocalDate.ofEpochDay(recordDate)
    )
}

internal fun ExerciseRecordItemEntity.toLocalEntity(): ExerciseRecordDbEntity {
    return ExerciseRecordDbEntity(
        memo = memo,
        recordTime = recordTime,
        recordDate = recordDate.toEpochDay()
    )
}
