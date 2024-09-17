package com.example.sparring.record

import com.example.sparring.model.SparringRecord
import com.example.sparring.model.mockSparingRecords
import com.seungsu.common.base.ViewEffect
import com.seungsu.common.base.ViewIntent
import com.seungsu.common.base.ViewState

sealed interface SparringRecordIntent: ViewIntent

data class SparringRecordState(
    val records: List<SparringRecord> = mockSparingRecords
): ViewState

sealed interface SparringRecordEffect: ViewEffect
