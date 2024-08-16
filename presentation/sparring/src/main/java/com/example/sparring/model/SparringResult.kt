package com.example.sparring.model

data class SparringResult(
    val win: Int = 0,
    val learn: Int = 0
) {
    val total: Int
        get() = win + learn
}
