package com.seungsu.domain.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    suspend fun setCurrentContent(contentName: String)
    val currentContent: Flow<String>
}