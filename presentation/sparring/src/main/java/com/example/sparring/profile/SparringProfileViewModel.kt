package com.example.sparring.profile

import androidx.lifecycle.viewModelScope
import com.seungsu.common.model.ContentsType
import com.seungsu.core.base.MVIViewModel
import com.seungsu.domain.usecase.UpdateCurrentContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SparringProfileViewModel @Inject constructor(
    private val updateCurrentContentUseCase: UpdateCurrentContentUseCase
): MVIViewModel<SparringProfileIntent, SparringProfileState, SparringProfileEffect>() {

    override fun createInitialState() = SparringProfileState()

    override suspend fun processIntent(intent: SparringProfileIntent) {
        when (intent) {
            is SparringProfileIntent.OnChangeNickName -> setState { copy(nickName = intent.nickName) }
            is SparringProfileIntent.OnChangeName -> setState { copy(name = intent.name) }
            is SparringProfileIntent.OnChangeGymName -> setState { copy(gymName = intent.gymName) }
            SparringProfileIntent.OnClearGymName -> setState { copy(gymName = "") }
            SparringProfileIntent.OnClearName -> setState { copy(name = "") }
            SparringProfileIntent.OnClearNickName -> setState { copy(nickName = "") }
            is SparringProfileIntent.OnChangeContent -> processOnChangeContent(intent.content)
        }
    }

    private fun processOnChangeContent(content: ContentsType) {
        viewModelScope.launch {
            updateCurrentContentUseCase(content.code)
            setEffect(SparringProfileEffect.ShowRestartDialog)
        }
    }
}