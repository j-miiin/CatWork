package com.example.catwork.domain

enum class Emoji(
    val id: Int,
    val resName: String
) {

    PERFECT(1, "perfect"),

    GOOD(2, "good"),

    SOSO(3, "soso"),

    BAD(4, "bad"),

    SAD(5, "sad"),

    TIRED(6, "tired"),

    SICK(7, "sick"),

    NONE(0, "none"),
}