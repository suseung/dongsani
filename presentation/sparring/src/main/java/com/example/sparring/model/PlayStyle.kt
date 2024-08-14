package com.example.sparring.model

data class PlayStyle(
    val id: Int,
    val styleName: String
)

val PLAYSTYLEs = listOf(
    PlayStyle(
        id = 0,
        styleName = "All-rounder"
    ),
    PlayStyle(
        id = 1,
        styleName = "Guard"
    ),
    PlayStyle(
        id = 2,
        styleName = "Half-Guard"
    ),
    PlayStyle(
        id = 3,
        styleName = "Top"
    ),
    PlayStyle(
        id = 4,
        styleName = "Lapel"
    ),
    PlayStyle(
        id = 5,
        styleName = "Lasso"
    ),
    PlayStyle(
        id = 6,
        styleName = "Choke"
    ),
    PlayStyle(
        id = 7,
        styleName = "Physical"
    ),
    PlayStyle(
        id = 8,
        styleName = "Unique"
    ),
    PlayStyle(
        id = 9,
        styleName = "Submission"
    )
)
