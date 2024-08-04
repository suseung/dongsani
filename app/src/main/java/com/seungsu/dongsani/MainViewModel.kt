package com.seungsu.dongsani

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seungsu.domain.usecase.GetCurrentContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentContentUseCase: GetCurrentContentUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var currentContent = savedStateHandle.getStateFlow(KEY_CURRENT_CONTENT, "")

    init {
        viewModelScope.launch {
            val currentContent = getCurrentContentUseCase(Unit).first()
            savedStateHandle[KEY_CURRENT_CONTENT] = currentContent
        }
    }

    companion object {
        private const val KEY_CURRENT_CONTENT = "current_content"
    }
}