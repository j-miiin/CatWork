package com.example.catwork.ext

import android.util.Log

fun getRandomID(): String {
    val charset = ('0'..'9') + ('a'..'z') + ('A'..'Z')
    return (1..10)
        .map { charset.random() }
        .joinToString("")
        + System.currentTimeMillis()
}