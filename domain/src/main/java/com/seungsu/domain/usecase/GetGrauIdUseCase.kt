package com.seungsu.domain.usecase

import com.seungsu.domain.base.FlowUseCase
import com.seungsu.domain.preference.PreferenceStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetGrauIdUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): FlowUseCase<Unit, Int>() {

    override fun execute(params: Unit): Flow<Int> = flow {
        emit(preferenceStorage.grauId.first())
    }
}
