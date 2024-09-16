package com.example.sparring.profile

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.example.sparring.model.ProfileInfo
import com.seungsu.common.base.MVIViewModel
import com.seungsu.common.eventbus.Event
import com.seungsu.common.eventbus.EventBus
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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getProfileImagePathUseCase: GetProfileImagePathUseCase,
    getProfileImageUriUseCase: GetProfileImageUriUseCase,
    getUserNameUseCase: GetUserNameUseCase,
    getUserNickNameUseCase: GetUserNickNameUseCase,
    getGymNameUseCase: GetGymNameUseCase,
    getBeltIdUseCase: GetBeltIdUseCase,
    getGrauIdUseCase: GetGrauIdUseCase,
    getPlayStyleIdsUseCase: GetPlayStyleIdsUseCase,
    private val eventBus: EventBus
) : MVIViewModel<ProfileIntent, ProfileState, ProfileEffect>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val profileResult = loadDataSignal
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
    init {
        launch {
            launch {
                profileResult.collect { apiResult ->
                    when (apiResult) {
                        is ApiResult.Success -> {
                            val data = apiResult.data
                            setState {
                                ProfileState(
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

                        else -> setState { ProfileState() }
                    }
                }
            }
            launch {
                eventBus.collect {
                    when (it) {
                        is Event.Sparring.OnProfileChanged -> onRefresh()
                    }
                }
            }
        }
    }

    override fun createInitialState() = ProfileState()

    override suspend fun processIntent(intent: ProfileIntent) = Unit
}
