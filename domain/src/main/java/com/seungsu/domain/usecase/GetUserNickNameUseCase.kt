package com.seungsu.domain.usecase

import com.seungsu.domain.base.FlowUseCase
import com.seungsu.domain.preference.PreferenceStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetUserNickNameUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): FlowUseCase<Unit, String>() {

    override fun execute(params: Unit): Flow<String> = flow {
        emit(preferenceStorage.nickName.first())
    }
}
