package com.example.sparring.addrecord

import com.seungsu.common.base.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SparringAddRecordViewModel @Inject constructor(

): MVIViewModel<SparringAddRecordIntent, SparringAddRecordState, SparringAddRecordEffect>() {

    override fun createInitialState() = SparringAddRecordState()

    override suspend fun processIntent(intent: SparringAddRecordIntent) {
        when (intent) {
            is SparringAddRecordIntent.OnChangeGymName -> processOnChangeGymName(intent.gymName)
            is SparringAddRecordIntent.OnChangeMemo -> processOnChangeMemo(intent.memo)
            is SparringAddRecordIntent.OnChangeOpponentName -> processOnChangeOpponentName(intent.opponentName)
            SparringAddRecordIntent.OnClearGymName -> processOnClearGymName()
            SparringAddRecordIntent.OnClearMemo -> processOnClearMemo()
            SparringAddRecordIntent.OnClearOpponentName -> processOnClearOpponentName()
            SparringAddRecordIntent.OnClickSaveSparringRecord -> setEffect(SparringAddRecordEffect.OnSaveSparringRecord)
        }
    }

    private fun processOnChangeGymName(gymName: String) = currentState {
        setState { copy(gymName = gymName) }
    }

    private fun processOnChangeMemo(memo: String) = currentState {
        setState { copy(memo = memo) }
    }

    private fun processOnChangeOpponentName(opponentName: String) = currentState {
        setState { copy(opponentName = opponentName) }
    }

    private fun processOnClearGymName() = currentState {
        setState { copy(gymName = "") }
    }

    private fun processOnClearOpponentName() = currentState {
        setState { copy(opponentName = "") }
    }

    private fun processOnClearMemo() = currentState {
        setState { copy(memo = "") }
    }
}
