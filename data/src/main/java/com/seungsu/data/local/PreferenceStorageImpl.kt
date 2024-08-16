package com.seungsu.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
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

    override suspend fun updateUserName(userName: String) {
        prefDatastore.edit { pref ->
            pref[KEY_USER_NAME] = userName
        }
    }

    override val userName: Flow<String> = prefDatastore.data
        .map { it[KEY_USER_NAME] ?: "" }

    override suspend fun updateNickName(nickName: String) {
        prefDatastore.edit { pref ->
            pref[KEY_USER_NICK_NAME] = nickName
        }
    }

    override val nickName: Flow<String> = prefDatastore.data
    .map { it[KEY_USER_NICK_NAME] ?: "" }

    override suspend fun updateGymName(gymName: String) {
        prefDatastore.edit { pref ->
            pref[KEY_GYM_NAME] = gymName
        }
    }

    override val gymName: Flow<String> = prefDatastore.data
        .map { it[KEY_GYM_NAME] ?: "" }

    override suspend fun updateBeltId(beltId: Int) {
        prefDatastore.edit { pref ->
            pref[KEY_BELT_ID] = beltId
        }
    }

    override val beltId: Flow<Int> = prefDatastore.data
        .map { it[KEY_BELT_ID] ?: -1 }

    override suspend fun updateGrauId(grauId: Int) {
        prefDatastore.edit { pref ->
            pref[KEY_GRAU_ID] = grauId
        }
    }

    override val grauId: Flow<Int> = prefDatastore.data
    .map { it[KEY_GRAU_ID] ?: -1 }

    override suspend fun updatePlayStyleIds(playStyleIds: List<Int>) {
        prefDatastore.edit { pref ->
            pref[KEY_PLAY_STYLES] = playStyleIds.map { it.toString() }.toSet()
        }
    }

    override val playStyles: Flow<List<Int>> = prefDatastore.data
        .map {
            it[KEY_PLAY_STYLES]?.let { playStyles ->
                playStyles.map { it.toInt() }
            } ?: emptyList()
        }

    companion object {
        private val KEY_CURRENT_CONTENT = stringPreferencesKey("currentContent")
        private val KEY_USER_NAME = stringPreferencesKey("userName")
        private val KEY_USER_NICK_NAME = stringPreferencesKey("userNickName")
        private val KEY_GYM_NAME = stringPreferencesKey("gymName")
        private val KEY_BELT_ID = intPreferencesKey("beltId")
        private val KEY_GRAU_ID = intPreferencesKey("grauId")
        private val KEY_PLAY_STYLES = stringSetPreferencesKey("playStyles")
    }
}
