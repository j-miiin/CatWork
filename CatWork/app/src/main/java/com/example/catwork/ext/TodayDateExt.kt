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

fun getLastOrNextMonthOrDay(selectedYear: Int, selectedMonth: Int, selectedDay: Int, isLast: Boolean, isMonth: Boolean): List<Int> {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, selectedYear)
    calendar.set(Calendar.MONTH, selectedMonth)
    calendar.set(Calendar.DAY_OF_MONTH, selectedDay)

    if (isLast && isMonth) calendar.add(Calendar.MONTH, -1)  // 이전달
    else if (!isLast && isMonth) calendar.add(Calendar.MONTH, 1)    // 다음달
    else if (isLast && !isMonth) calendar.add(Calendar.DATE, -1)    // 이전날
    else calendar.add(Calendar.DATE, 1)    // 다음날

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    return listOf(year, month, day)
}
