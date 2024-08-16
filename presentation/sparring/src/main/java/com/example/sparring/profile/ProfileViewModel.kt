package com.example.sparring.profile

import androidx.lifecycle.viewModelScope
import com.example.sparring.model.ProfileInfo
import com.seungsu.core.base.MVIViewModel
import com.seungsu.domain.base.ApiResult
import com.seungsu.domain.base.asResult
import com.seungsu.domain.usecase.GetBeltIdUseCase
import com.seungsu.domain.usecase.GetGrauIdUseCase
import com.seungsu.domain.usecase.GetGymNameUseCase
import com.seungsu.domain.usecase.GetPlayStyleIdsUseCase
import com.seungsu.domain.usecase.GetUserNameUseCase
import com.seungsu.domain.usecase.GetUserNickNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    getUserNameUseCase: GetUserNameUseCase,
    getUserNickNameUseCase: GetUserNickNameUseCase,
    getGymNameUseCase: GetGymNameUseCase,
    getBeltIdUseCase: GetBeltIdUseCase,
    getGrauIdUseCase: GetGrauIdUseCase,
    getPlayStyleIdsUseCase: GetPlayStyleIdsUseCase
) : MVIViewModel<ProfileIntent, ProfileState, ProfileEffect>() {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val profileResult = loadDataSignal
        .flatMapLatest {
            combine(
                getUserNameUseCase(Unit),
                getUserNickNameUseCase(Unit),
                getGymNameUseCase(Unit),
                getBeltIdUseCase(Unit),
                getGrauIdUseCase(Unit),
                getPlayStyleIdsUseCase(Unit)
            ) { values: Array<Any> ->
                ProfileInfo(
                    name = values[0] as String,
                    nickName = values[1] as String,
                    gymName = values[2] as String,
                    beltId = values[3] as Int,
                    grauId = values[4] as Int,
                    playStyleIds = values[5] as List<Int>
                )
            }
        }.asResult()
        .stateIn(viewModelScope, SharingStarted.Lazily, ApiResult.Loading)
    init {
        viewModelScope.launch {
            profileResult.collect { apiResult ->
                when (apiResult) {
                    is ApiResult.Success -> {
                        val data = apiResult.data
                        setState {
                            ProfileState(
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
                        setState { ProfileState() }
                    }
                }
            }
        }
    }

    override fun createInitialState() = ProfileState()

    override suspend fun processIntent(intent: ProfileIntent) {
        when (intent) {
            ProfileIntent.OnRefresh -> onRefresh()
        }
    }
}
