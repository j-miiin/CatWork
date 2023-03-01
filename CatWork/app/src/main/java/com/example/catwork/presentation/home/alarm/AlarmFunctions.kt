package com.example.catwork.presentation.home.alarm

import android.app.PendingIntent
import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class AlarmFunctions(private val context: Context) {

    private lateinit var pendingIntent: PendingIntent

    private val ioScope by lazy { CoroutineScope(Dispatchers.IO) }

    fun callAlarm(time: String, alarm_code: Int, content: String) {
        
    }
}