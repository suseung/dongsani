package com.seungsu.domain.model

import java.time.LocalDate

data class ExerciseRecordItemEntity(
    val memo: String,
    val recordTime: Long,
    val recordDate: LocalDate
)
