package com.example.sparring.profile

import com.seungsu.core.base.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SparringProfileViewModel @Inject constructor(

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
        }
    }
}