package com.example.catwork.domain

enum class Emoji(
    val id: Int,
    val resName: String
) {

    PERFECT(1, ""),

    GOOD(2, ""),

    SOSO(3, ""),

    BAD(4, ""),

    SAD(5, ""),

    TIRED(6, ""),

    SICK(7, ""),

    NONE(0, ""),
}