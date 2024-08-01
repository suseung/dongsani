package com.seungsu.setting

import androidx.compose.ui.graphics.Color
import com.seungsu.core.base.ViewEffect
import com.seungsu.core.base.ViewIntent
import com.seungsu.core.base.ViewState

sealed interface ExerciseSettingIntent: ViewIntent

data class ExerciseSettingState(
    val temp: String = "",
    val color: Color = Color.Gray,
    val colorText: String = ""
): ViewState

sealed interface ExerciseSettingEffect: ViewEffect