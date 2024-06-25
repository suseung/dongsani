package com.seungsu.model

import com.seungsu.common.HOUR_TO_SEC
import com.seungsu.common.MIN_TO_SEC

data class ExerciseRecordItem(
    val memo: String,
    val recordTime: Long
) {
    val parsedRecordTime: String
        get() {
            val hour = recordTime / HOUR_TO_SEC
            val min = (recordTime / MIN_TO_SEC) % MIN_TO_SEC
            val sec = recordTime % MIN_TO_SEC
            return String.format("%02d:%02d:%02d", hour, min, sec)
        }
}
