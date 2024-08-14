package com.example.sparring.model

data class Grau(
    val id: Int,
    val grau: GrauType
)

val GRAUs = listOf(
    Grau(
        id = 0,
        grau = GrauType.ZERO
    ),
    Grau(
        id = 1,
        grau = GrauType.ONE
    ),
    Grau(
        id = 2,
        grau = GrauType.TWO
    ),
    Grau(
        id = 3,
        grau = GrauType.THREE
    ),
    Grau(
        id = 4,
        grau = GrauType.FOUR
    )
)
