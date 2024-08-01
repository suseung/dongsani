package com.seungsu.model

import com.seungsu.common.HOUR_TO_SEC
import com.seungsu.common.MIN_TO_SEC
import com.seungsu.domain.model.ExerciseRecordItemEntity
import java.time.LocalDate

data class ExerciseRecordItem(
    val memo: String = "",
    val recordTime: Long = 1234567L,
    val recordDate: LocalDate = LocalDate.now()
) {
    val parsedRecordTime: String
        get() {
            val hour = recordTime / HOUR_TO_SEC
            val min = (recordTime / MIN_TO_SEC) % MIN_TO_SEC
            val sec = recordTime % MIN_TO_SEC
            return String.format("%02d:%02d:%02d", hour, min, sec)
        }
}

fun ExerciseRecordItemEntity.toUiModel(): ExerciseRecordItem {
    return ExerciseRecordItem(
        memo = memo,
        recordTime = recordTime,
        recordDate = recordDate
    )
}

fun ExerciseRecordItem.toDomainModel(): ExerciseRecordItemEntity {
    return ExerciseRecordItemEntity(
        memo = memo,
        recordTime = recordTime,
        recordDate = recordDate
    )
}
