package com.seungsu.domain.usecase

import com.seungsu.domain.base.UseCase
import com.seungsu.domain.preference.PreferenceStorage
import javax.inject.Inject

class UpdateGrauIdUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): UseCase<Int, Unit>() {

    override suspend fun execute(params: Int) {
        preferenceStorage.updateGrauId(params)
    }
}
