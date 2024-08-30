package com.example.sparring.makeprofile

import androidx.lifecycle.viewModelScope
import com.example.sparring.model.ProfileInfo
import com.seungsu.common.INVALID_INT
import com.seungsu.common.model.ContentsType
import com.seungsu.common.base.MVIViewModel
import com.seungsu.domain.base.ApiResult
import com.seungsu.domain.base.asResult
import com.seungsu.domain.usecase.GetBeltIdUseCase
import com.seungsu.domain.usecase.GetGrauIdUseCase
import com.seungsu.domain.usecase.GetGymNameUseCase
import com.seungsu.domain.usecase.GetPlayStyleIdsUseCase
import com.seungsu.domain.usecase.GetProfileImagePathUseCase
import com.seungsu.domain.usecase.GetUserNameUseCase
import com.seungsu.domain.usecase.GetUserNickNameUseCase
import com.seungsu.domain.usecase.UpdateBeltIdUseCase
import com.seungsu.domain.usecase.UpdateCurrentContentUseCase
import com.seungsu.domain.usecase.UpdateGrauIdUseCase
import com.seungsu.domain.usecase.UpdateGymNameUseCase
import com.seungsu.domain.usecase.UpdatePlayStyleIdsUseCase
import com.seungsu.domain.usecase.UpdateProfileImagePathUseCase
import com.seungsu.domain.usecase.UpdateUserNameUseCase
import com.seungsu.domain.usecase.UpdateUserNickNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch

@HiltViewModel
class SparringMakeProfileViewModel @Inject constructor(
    getProfileImagePathUseCase: GetProfileImagePathUseCase,
    getUserNameUseCase: GetUserNameUseCase,
    getUserNickNameUseCase: GetUserNickNameUseCase,
    getGymNameUseCase: GetGymNameUseCase,
    getBeltIdUseCase: GetBeltIdUseCase,
    getGrauIdUseCase: GetGrauIdUseCase,
    getPlayStyleIdsUseCase: GetPlayStyleIdsUseCase,
    private val updateProfileImagePathUseCase: UpdateProfileImagePathUseCase,
    private val updateCurrentContentUseCase: UpdateCurrentContentUseCase,
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateUserNickNameUseCase: UpdateUserNickNameUseCase,
    private val updateGymNameUseCase: UpdateGymNameUseCase,
    private val updateBeltIdUseCase: UpdateBeltIdUseCase,
    private val updateGrauIdUseCase: UpdateGrauIdUseCase,
    private val updatePlayStyleIdsUseCase: UpdatePlayStyleIdsUseCase
) : MVIViewModel<SparringMakeProfileIntent, SparringMakeProfileState, SparringMakeProfileEffect>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val makeProfileResult = loadDataSignal
        .flatMapLatest {
            combine(
                getProfileImagePathUseCase(Unit),
                getUserNameUseCase(Unit),
                getUserNickNameUseCase(Unit),
                getGymNameUseCase(Unit),
                getBeltIdUseCase(Unit),
                getGrauIdUseCase(Unit),
                getPlayStyleIdsUseCase(Unit)
            ) { values: Array<Any> ->
                ProfileInfo(
                    profileImagePath = values[0] as String,
                    name = values[1] as String,
                    nickName = values[2] as String,
                    gymName = values[3] as String,
                    beltId = values[4] as Int,
                    grauId = values[5] as Int,
                    playStyleIds = values[6] as List<Int>
                )
            }
        }.asResult()
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading)

    override fun createInitialState() = SparringMakeProfileState()

    init {
        viewModelScope.launch {
            makeProfileResult.collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        val data = apiResult.data
                        setState {
                            SparringMakeProfileState(
                                profileImagePath = data.profileImagePath,
                                name = data.name,
                                nickName = data.nickName,
                                gymName = data.gymName,
                                currentBeltId = data.beltId,
                                currentGrauId = data.grauId,
                                currentPlayStyleIds = data.playStyleIds
                            )
                        }

                    }

                    else -> {
                        setState {
                            SparringMakeProfileState()
                        }
                    }
                }
            }
        }
    }

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
            SparringMakeProfileIntent.OnClickSaveProfile -> processSaveProfile()
            is SparringMakeProfileIntent.OnSelectPlayStyle -> setState {
                if (currentPlayStyleIds.contains(intent.playStyleId)) {
                    copy(currentPlayStyleIds = currentPlayStyleIds - intent.playStyleId)
                } else {
                    copy(currentPlayStyleIds = currentPlayStyleIds + intent.playStyleId)
                }
            }

            SparringMakeProfileIntent.OnClickDeleteProfile -> setState {
                copy(profileImagePath = "")
            }
            SparringMakeProfileIntent.OnClickGetPhotoFromGallery -> setToastEffect("갤러리~")

            is SparringMakeProfileIntent.OnChangeProfileImage -> setState {
                copy(profileImagePath = intent.filePath)
            }
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

    private fun processSaveProfile() = currentState {
        viewModelScope.launch {
            val profileImageAsync = async { updateProfileImagePathUseCase(profileImagePath) }
            val userNameAsync = async { updateUserNameUseCase(name) }
            val userNickNameAsync = async { updateUserNickNameUseCase(nickName) }
            val gymNameAsync = async { updateGymNameUseCase(gymName) }
            val beltIdAsync = async { updateBeltIdUseCase(currentBeltId) }
            val grauIdAsync = async { updateGrauIdUseCase(currentGrauId) }
            val playStyleIdsAsync = async { updatePlayStyleIdsUseCase(currentPlayStyleIds) }

            listOf(
                profileImageAsync,
                userNameAsync,
                userNickNameAsync,
                gymNameAsync,
                beltIdAsync,
                grauIdAsync,
                playStyleIdsAsync
            ).joinAll()
            setToastEffect("프로필 편집 완료~")
            setEffect(SparringMakeProfileEffect.NavigateToProfile)
        }
    }
}
