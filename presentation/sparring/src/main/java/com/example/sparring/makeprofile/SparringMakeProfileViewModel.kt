package com.example.sparring.makeprofile

import androidx.lifecycle.viewModelScope
import com.seungsu.common.INVALID_INT
import com.seungsu.common.model.ContentsType
import com.seungsu.core.base.MVIViewModel
import com.seungsu.domain.usecase.UpdateCurrentContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class SparringMakeProfileViewModel @Inject constructor(
    private val updateCurrentContentUseCase: UpdateCurrentContentUseCase
): MVIViewModel<SparringMakeProfileIntent, SparringMakeProfileState, SparringMakeProfileEffect>() {

    override fun createInitialState() = SparringMakeProfileState()

    override suspend fun processIntent(intent: SparringMakeProfileIntent) {
        when (intent) {
            is SparringMakeProfileIntent.OnChangeNickName -> setState { copy(nickName = intent.nickName) }
            is SparringMakeProfileIntent.OnChangeName -> setState { copy(name = intent.name) }
            is SparringMakeProfileIntent.OnChangeGymName -> setState { copy(gymName = intent.gymName) }
            SparringMakeProfileIntent.OnClearGymName -> setState { copy(gymName = "") }
            SparringMakeProfileIntent.OnClearName -> setState { copy(name = "") }
            SparringMakeProfileIntent.OnClearNickName -> setState { copy(nickName = "") }
            is SparringMakeProfileIntent.OnChangeContent -> processOnChangeContent(intent.content)
            is SparringMakeProfileIntent.OnChangeLevel -> processChangeLevel(intent.beltId, intent.grauId)
            SparringMakeProfileIntent.OnClickSaveProfile -> setToastEffect("완료우~~")
            is SparringMakeProfileIntent.OnSelectPlayStyle -> setState {
                if (currentPlayStyleIds.contains(intent.playStyleId)) {
                    copy(currentPlayStyleIds = currentPlayStyleIds - intent.playStyleId)
                } else {
                    copy(currentPlayStyleIds = currentPlayStyleIds + intent.playStyleId)
                }
            }

            SparringMakeProfileIntent.OnClickDeleteProfile -> setToastEffect("프로필 삭제~")
            SparringMakeProfileIntent.OnClickGetPhotoFromGallery -> setToastEffect("갤러리~")
            SparringMakeProfileIntent.OnClickTakePhoto -> setToastEffect("사진 찍기~")
        }
    }

    private fun processOnChangeContent(content: ContentsType) {
        viewModelScope.launch {
            updateCurrentContentUseCase(content.code)
            setEffect(SparringMakeProfileEffect.ShowRestartDialog)
        }
    }

    private fun processChangeLevel(beltId: Int, grauId: Int) {
        when {
            beltId == INVALID_INT -> setEffect(SparringMakeProfileEffect.ShowSelectBeltToast)
            grauId == INVALID_INT -> setEffect(SparringMakeProfileEffect.ShowSelectGrauToast)
            else -> {
                setState {
                    copy(
                        currentBeltId = beltId,
                        currentGrauId = grauId
                    )
                }
            }
        }
    }
}
