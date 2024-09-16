package com.example.sparring.profile

import android.net.Uri
import com.example.sparring.model.BELTs
import com.example.sparring.model.BeltType
import com.example.sparring.model.GRAUs
import com.example.sparring.model.GrauType
import com.example.sparring.model.PLAYSTYLEs
import com.example.sparring.model.PlayStyle
import com.example.sparring.model.SparringResult
import com.seungsu.common.INVALID_INT
import com.seungsu.common.base.ViewEffect
import com.seungsu.common.base.ViewIntent
import com.seungsu.common.base.ViewState

sealed interface ProfileIntent : ViewIntent

data class ProfileState(
    val profileImagePath: String = "",
    val profileImageUri: Uri = Uri.EMPTY,
    val name: String = "",
    val nickName: String = "",
    val gymName: String = "",
    val currentBeltId: Int = INVALID_INT,
    val currentGrauId: Int = INVALID_INT,
    val currentPlayStyleIds: List<Int> = emptyList(),
    val sparringResult: SparringResult = SparringResult()
) : ViewState {
    val currentBelt: BeltType
        get() = if (currentBeltId == INVALID_INT) {
            BeltType.WHITE
        } else {
            BELTs[currentBeltId].belt
        }

    val currentGrau: GrauType
        get() = if (currentGrauId == INVALID_INT) {
            GrauType.ZERO
        } else {
            GRAUs[currentGrauId].grau
        }

    val currentPlayStyles: List<PlayStyle>
        get() = if (currentPlayStyleIds.isEmpty()) {
            emptyList()
        } else {
            currentPlayStyleIds.mapNotNull { styleId ->
                PLAYSTYLEs.find { it.id == styleId }
            }
        }
}

sealed interface ProfileEffect : ViewEffect
