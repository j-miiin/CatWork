package com.example.catwork.ext

import java.util.*
import kotlin.collections.List

fun getTodayDate(): List<Int> {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return listOf(year, month, day)
}

fun getTodayDateString(): String {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    var dateStr = year.toString()

    if (month < 10) dateStr += "0" + month
    else dateStr += month

    if (day < 10) dateStr += "0" + day
    else dateStr += day

    return dateStr
}

fun getSelectedDateString(year: Int, month: Int, day: Int): String {
    var dateStr = year.toString()

    if (month < 10) dateStr += "0" + month
    else dateStr += month

    if (day < 10) dateStr += "0" + day
    else dateStr += day

    return dateStr
}

fun getYesterdayDate(): List<Int> {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
    calendar.add(Calendar.DATE, -1)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return listOf(year, month, day)
}

fun getTomorrowDate(): List<Int> {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
    calendar.add(Calendar.DATE, 1)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return listOf(year, month, day)
}