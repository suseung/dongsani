package com.seungsu.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.seungsu.domain.preference.PreferenceStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferenceStorageImpl @Inject constructor(
    private val prefDatastore: DataStore<Preferences>
): PreferenceStorage {

    override suspend fun setCurrentContent(contentName: String) {
        prefDatastore.edit { pref ->
            pref[KEY_CURRENT_CONTENT] = contentName
        }
    }

    override val currentContent: Flow<String> = prefDatastore.data
        .map { it[KEY_CURRENT_CONTENT] ?: "" }

    companion object {
        private val KEY_CURRENT_CONTENT = stringPreferencesKey("currentContent")
    }
}