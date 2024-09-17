package com.example.sparring.addrecord

import com.seungsu.common.base.ViewEffect
import com.seungsu.common.base.ViewIntent
import com.seungsu.common.base.ViewState

sealed interface SparringAddRecordIntent: ViewIntent {
    data object OnClearGymName: SparringAddRecordIntent
    data object OnClearOpponentName: SparringAddRecordIntent
    data object OnClearMemo: SparringAddRecordIntent
    data object OnClickSaveSparringRecord: SparringAddRecordIntent

    @JvmInline
    value class OnChangeGymName(val gymName: String): SparringAddRecordIntent

    @JvmInline
    value class OnChangeOpponentName(val opponentName: String): SparringAddRecordIntent

    @JvmInline
    value class OnChangeMemo(val memo: String): SparringAddRecordIntent
}

data class SparringAddRecordState(
    val gymName: String = "",
    val opponentName: String = "",
    val memo: String = ""
): ViewState {
    val isSavedEnabled: Boolean
        get() = gymName.isNotEmpty() && opponentName.isNotEmpty()
}

sealed interface SparringAddRecordEffect: ViewEffect {
    data object OnSaveSparringRecord: SparringAddRecordEffect
}
