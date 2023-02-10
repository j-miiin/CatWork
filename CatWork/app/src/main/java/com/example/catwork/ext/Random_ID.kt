package com.example.catwork.ext

fun getRandomID(): String {
    val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
    return (1..10)
        .map { charset.random() }
        .joinToString("")
        + System.currentTimeMillis()
}