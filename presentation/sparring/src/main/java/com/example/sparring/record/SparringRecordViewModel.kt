package com.example.sparring.record

import com.seungsu.common.base.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SparringRecordViewModel @Inject constructor(

) : MVIViewModel<SparringRecordIntent, SparringRecordState, SparringRecordEffect>() {

    override fun createInitialState() = SparringRecordState()

    override suspend fun processIntent(intent: SparringRecordIntent) {}
}