package com.seungsu.setting

import com.seungsu.core.base.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseSettingViewModel @Inject constructor(

): MVIViewModel<ExerciseSettingIntent, ExerciseSettingState, ExerciseSettingEffect>() {
    override fun createInitialState() = ExerciseSettingState()

    override suspend fun processIntent(intent: ExerciseSettingIntent) {
        TODO("Not yet implemented")
    }
}