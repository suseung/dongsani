package com.seungsu.domain.usecase

import com.seungsu.domain.base.UseCase
import com.seungsu.domain.preference.PreferenceStorage
import javax.inject.Inject

class UpdatePlayStyleIdsUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): UseCase<List<Int>, Unit>() {

    override suspend fun execute(params: List<Int>) {
        preferenceStorage.updatePlayStyleIds(params)
    }
}
