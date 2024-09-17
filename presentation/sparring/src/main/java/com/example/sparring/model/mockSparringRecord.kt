package com.example.sparring.model

import android.net.Uri

val mockSparingRecords = listOf(
    SparringRecord(
        opponentInfo = ProfileInfo(
            userId = 0,
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            name = "name1",
            nickName = "nickName1",
            gymName = "gymName1",
            beltId = 0,
            grauId = 2,
            playStyleIds = emptyList()
        ),
        resultInfo = SparringResult(
            win = 1,
            learn = 2
        )
    ),
    SparringRecord(
        opponentInfo = ProfileInfo(
            userId = 1,
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            name = "name2",
            nickName = "nickName2",
            gymName = "gymName2",
            beltId = 1,
            grauId = 2,
            playStyleIds = emptyList()
        ),
        resultInfo = SparringResult(
            win = 2,
            learn = 3
        )
    ),
    SparringRecord(
        opponentInfo = ProfileInfo(
            userId = 2,
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            name = "name3",
            nickName = "nickName3",
            gymName = "gymName3",
            beltId = 2,
            grauId = 2,
            playStyleIds = emptyList()
        ),
        resultInfo = SparringResult(
            win = 3,
            learn = 4
        )
    ),
    SparringRecord(
        opponentInfo = ProfileInfo(
            userId = 3,
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            name = "name4",
            nickName = "nickName4",
            gymName = "gymName4",
            beltId = 2,
            grauId = 0,
            playStyleIds = emptyList()
        ),
        resultInfo = SparringResult(
            win = 5,
            learn = 0
        )
    ),
    SparringRecord(
        opponentInfo = ProfileInfo(
            userId = 4,
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            name = "name5",
            nickName = "nickName5",
            gymName = "gymNam5",
            beltId = 3,
            grauId = 4,
            playStyleIds = emptyList()
        ),
        resultInfo = SparringResult(
            win = 2,
            learn = 7
        )
    ),
    SparringRecord(
        opponentInfo = ProfileInfo(
            userId = 5,
            profileImagePath = "",
            profileImageUri = Uri.EMPTY,
            name = "name6",
            nickName = "nickName6",
            gymName = "gymName6",
            beltId = 4,
            grauId = 4,
            playStyleIds = emptyList()
        ),
        resultInfo = SparringResult(
            win = 1,
            learn = 1
        )
    )
)
