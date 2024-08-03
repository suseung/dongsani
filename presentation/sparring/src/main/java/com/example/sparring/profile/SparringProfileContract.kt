package com.example.sparring.profile

import com.seungsu.core.base.ViewEffect
import com.seungsu.core.base.ViewIntent
import com.seungsu.core.base.ViewState

sealed interface SparringProfileIntent: ViewIntent {

    @JvmInline
    value class OnChangeName(val name: String): SparringProfileIntent

    @JvmInline
    value class OnChangeNickName(val nickName: String): SparringProfileIntent

    @JvmInline
    value class OnChangeGymName(val gymName: String): SparringProfileIntent

    data object OnClearName: SparringProfileIntent
    data object OnClearNickName: SparringProfileIntent
    data object OnClearGymName: SparringProfileIntent
}



data class SparringProfileState(
    val name: String = "",
    val nickName: String = "",
    val gymName: String = ""
): ViewState

sealed interface SparringProfileEffect: ViewEffect