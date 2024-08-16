package com.example.sparring.makeprofile

import com.example.sparring.model.BELTs
import com.example.sparring.model.GRAUs
import com.seungsu.common.INVALID_INT
import com.seungsu.common.model.ContentsType
import com.seungsu.core.base.ViewEffect
import com.seungsu.core.base.ViewIntent
import com.seungsu.core.base.ViewState

sealed interface SparringMakeProfileIntent: ViewIntent {

    @JvmInline
    value class OnChangeName(val name: String): SparringMakeProfileIntent

    @JvmInline
    value class OnChangeNickName(val nickName: String): SparringMakeProfileIntent

    @JvmInline
    value class OnChangeGymName(val gymName: String): SparringMakeProfileIntent

    data object OnClearName: SparringMakeProfileIntent
    data object OnClearNickName: SparringMakeProfileIntent
    data object OnClearGymName: SparringMakeProfileIntent

    @JvmInline
    value class OnChangeContent(val content: ContentsType): SparringMakeProfileIntent

    data class OnChangeLevel(val beltId: Int, val grauId: Int): SparringMakeProfileIntent

    @JvmInline
    value class OnSelectPlayStyle(val playStyleId: Int): SparringMakeProfileIntent

    data object OnClickSaveProfile: SparringMakeProfileIntent
    data object OnClickDeleteProfile: SparringMakeProfileIntent
    data object OnClickGetPhotoFromGallery: SparringMakeProfileIntent
    data object OnClickTakePhoto: SparringMakeProfileIntent
}



data class SparringMakeProfileState(
    val name: String = "",
    val nickName: String = "",
    val gymName: String = "",
    val currentBeltId: Int = INVALID_INT,
    val currentGrauId: Int = INVALID_INT,
    val currentPlayStyleIds: List<Int> = emptyList()
): ViewState {
    val currentLevel: String
        get() = if (currentGrauId == INVALID_INT || currentBeltId == INVALID_INT) {
            ""
        } else {
            "${BELTs[currentBeltId].belt.code}, ${GRAUs[currentGrauId].grau.code}"
        }

    val isSaveEnabled: Boolean
        get() = name.isNotEmpty() && nickName.isNotEmpty() && gymName.isNotEmpty() &&
                currentGrauId != INVALID_INT && currentBeltId != INVALID_INT && currentPlayStyleIds.isNotEmpty()
}

sealed interface SparringMakeProfileEffect: ViewEffect {
    data object ShowRestartDialog: SparringMakeProfileEffect
    data object ShowSelectBeltToast: SparringMakeProfileEffect
    data object ShowSelectGrauToast: SparringMakeProfileEffect
    data object NavigateToProfile: SparringMakeProfileEffect
}
