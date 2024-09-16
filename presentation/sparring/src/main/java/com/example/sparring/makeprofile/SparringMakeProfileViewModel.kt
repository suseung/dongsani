package com.example.sparring.makeprofile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.example.sparring.model.ProfileInfo
import com.seungsu.common.INVALID_INT
import com.seungsu.common.base.MVIViewModel
import com.seungsu.common.model.ContentsType
import com.seungsu.domain.base.ApiResult
import com.seungsu.domain.base.asResult
import com.seungsu.domain.usecase.GetBeltIdUseCase
import com.seungsu.domain.usecase.GetGrauIdUseCase
import com.seungsu.domain.usecase.GetGymNameUseCase
import com.seungsu.domain.usecase.GetPlayStyleIdsUseCase
import com.seungsu.domain.usecase.GetProfileImagePathUseCase
import com.seungsu.domain.usecase.GetProfileImageUriUseCase
import com.seungsu.domain.usecase.GetUserNameUseCase
import com.seungsu.domain.usecase.GetUserNickNameUseCase
import com.seungsu.domain.usecase.UpdateBeltIdUseCase
import com.seungsu.domain.usecase.UpdateCurrentContentUseCase
import com.seungsu.domain.usecase.UpdateGrauIdUseCase
import com.seungsu.domain.usecase.UpdateGymNameUseCase
import com.seungsu.domain.usecase.UpdatePlayStyleIdsUseCase
import com.seungsu.domain.usecase.UpdateProfileImagePathUseCase
import com.seungsu.domain.usecase.UpdateProfileImageUriUseCase
import com.seungsu.domain.usecase.UpdateUserNameUseCase
import com.seungsu.domain.usecase.UpdateUserNickNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.joinAll
import javax.inject.Inject

@HiltViewModel
class SparringMakeProfileViewModel @Inject constructor(
    getProfileImagePathUseCase: GetProfileImagePathUseCase,
    getProfileImageUriUseCase: GetProfileImageUriUseCase,
    getUserNameUseCase: GetUserNameUseCase,
    getUserNickNameUseCase: GetUserNickNameUseCase,
    getGymNameUseCase: GetGymNameUseCase,
    getBeltIdUseCase: GetBeltIdUseCase,
    getGrauIdUseCase: GetGrauIdUseCase,
    getPlayStyleIdsUseCase: GetPlayStyleIdsUseCase,
    private val updateProfileImagePathUseCase: UpdateProfileImagePathUseCase,
    private val updateProfileImageUriUseCase: UpdateProfileImageUriUseCase,
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
                getProfileImageUriUseCase(Unit),
                getUserNameUseCase(Unit),
                getUserNickNameUseCase(Unit),
                getGymNameUseCase(Unit),
                getBeltIdUseCase(Unit),
                getGrauIdUseCase(Unit),
                getPlayStyleIdsUseCase(Unit)
            ) { values: Array<Any> ->
                ProfileInfo(
                    profileImagePath = values[0] as String,
                    profileImageUri = (values[1] as String).takeIf { it != "" }?.toUri() ?: Uri.EMPTY,
                    name = values[2] as String,
                    nickName = values[3] as String,
                    gymName = values[4] as String,
                    beltId = values[5] as Int,
                    grauId = values[6] as Int,
                    playStyleIds = values[7] as List<Int>
                )
            }
        }.asResult()
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading)

    override fun createInitialState() = SparringMakeProfileState()

    init {
        launch {
            makeProfileResult.collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        val data = apiResult.data
                        setState {
                            SparringMakeProfileState(
                                profileImagePath = data.profileImagePath,
                                profileImageUri = data.profileImageUri,
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
            is SparringMakeProfileIntent.OnChangeLevel -> processChangeLevel(
                intent.beltId,
                intent.grauId
            )

            SparringMakeProfileIntent.OnClickSaveProfile -> processSaveProfile()
            is SparringMakeProfileIntent.OnSelectPlayStyle -> setState {
                if (currentPlayStyleIds.contains(intent.playStyleId)) {
                    copy(currentPlayStyleIds = currentPlayStyleIds - intent.playStyleId)
                } else {
                    copy(currentPlayStyleIds = currentPlayStyleIds + intent.playStyleId)
                }
            }

            SparringMakeProfileIntent.OnClickDeleteProfile -> setState {
                copy(
                    profileImagePath = "",
                    profileImageUri = Uri.EMPTY
                )
            }

            is SparringMakeProfileIntent.OnChangeProfileImage -> setState {
                copy(
                    profileImagePath = intent.filePath ?: "",
                    profileImageUri = intent.uri ?: Uri.EMPTY
                )
            }
        }
    }

    private fun processOnChangeContent(content: ContentsType) {
        launch {
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
        launch {
            val profileImageAsync = async { updateProfileImagePathUseCase(profileImagePath) }
            val profileImageUriAsync = async { updateProfileImageUriUseCase(profileImageUri.toString()) }
            val userNameAsync = async { updateUserNameUseCase(name) }
            val userNickNameAsync = async { updateUserNickNameUseCase(nickName) }
            val gymNameAsync = async { updateGymNameUseCase(gymName) }
            val beltIdAsync = async { updateBeltIdUseCase(currentBeltId) }
            val grauIdAsync = async { updateGrauIdUseCase(currentGrauId) }
            val playStyleIdsAsync = async { updatePlayStyleIdsUseCase(currentPlayStyleIds) }

            listOf(
                profileImageAsync,
                profileImageUriAsync,
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
