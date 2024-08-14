package com.example.sparring.model

data class Belt(
    val id: Int,
    val belt: BeltType
)

val BELTs = listOf(
    Belt(
        id = 0,
        belt = BeltType.WHITE
    ),
    Belt(
        id = 1,
        belt = BeltType.BLUE
    ),
    Belt(
        id = 2,
        belt = BeltType.PURPLE
    ),
    Belt(
        id = 3,
        belt = BeltType.BROWN
    ),
    Belt(
        id = 4,
        belt = BeltType.BLACK
    )
)
