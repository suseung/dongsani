package com.example.sparring.model

import android.net.Uri

data class ProfileInfo(
    val profileImagePath: String,
    val profileImageUri: Uri,
    val name: String,
    val nickName: String,
    val gymName: String,
    val beltId: Int,
    val grauId: Int,
    val playStyleIds: List<Int>
)
