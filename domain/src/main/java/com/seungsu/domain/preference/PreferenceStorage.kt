package com.seungsu.domain.preference

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {
    suspend fun setCurrentContent(contentName: String)
    val currentContent: Flow<String>

    suspend fun updateProfileImagePath(filePath: String)
    val profileImagePath: Flow<String>

    suspend fun updateUserName(userName: String)
    val userName: Flow<String>

    suspend fun updateNickName(nickName: String)
    val nickName: Flow<String>

    suspend fun updateGymName(gymName: String)
    val gymName: Flow<String>

    suspend fun updateBeltId(beltId: Int)
    val beltId: Flow<Int>

    suspend fun updateGrauId(grauId: Int)
    val grauId: Flow<Int>

    suspend fun updatePlayStyleIds(playStyleIds: List<Int>)
    val playStyles: Flow<List<Int>>
}
