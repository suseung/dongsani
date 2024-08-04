package com.seungsu.domain.usecase

import com.seungsu.domain.base.UseCase
import com.seungsu.domain.preference.PreferenceStorage
import javax.inject.Inject

class UpdateCurrentContentUseCase @Inject constructor(
    private val preferenceStorage: PreferenceStorage
): UseCase<String, Unit>() {

    override suspend fun execute(params: String) {
        preferenceStorage.setCurrentContent(params)
    }
}