package com.example.sparring.model

import android.net.Uri
import com.seungsu.common.INVALID_INT

data class ProfileInfo(
    val userId: Int = INVALID_INT,
    val profileImagePath: String,
    val profileImageUri: Uri,
    val name: String,
    val nickName: String,
    val gymName: String,
    val beltId: Int,
    val grauId: Int,
    val playStyleIds: List<Int>
)
