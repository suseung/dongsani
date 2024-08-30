package com.seungsu.setting

import androidx.compose.ui.graphics.Color
import com.seungsu.common.base.ViewEffect
import com.seungsu.common.base.ViewIntent
import com.seungsu.common.base.ViewState

sealed interface ExerciseSettingIntent: ViewIntent

data class ExerciseSettingState(
    val temp: String = "",
    val color: Color = Color.Gray,
    val colorText: String = ""
): ViewState

sealed interface ExerciseSettingEffect: ViewEffect
