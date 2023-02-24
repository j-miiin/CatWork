package com.example.catwork.ext

import java.util.*
import kotlin.collections.ArrayList

fun getTodayDate(): ArrayList<Int> {
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"), Locale.KOREA)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    

}