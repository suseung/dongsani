package com.seungsu.domain.usecase

import com.seungsu.domain.base.FlowUseCase
import com.seungsu.domain.preference.PreferenceStorage
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class GetPlayStyleIdsUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): FlowUseCase<Unit, List<Int>>() {

    override fun execute(params: Unit): Flow<List<Int>> = flow {
        emit(preferenceStorage.playStyles.first())
    }
}
