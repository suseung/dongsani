package com.example.sparring.model

data class ProfileInfo(
    val profileImagePath: String,
    val name: String,
    val nickName: String,
    val gymName: String,
    val beltId: Int,
    val grauId: Int,
    val playStyleIds: List<Int>
)
