package com.example.catwork.presentation.home.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class AlarmFunctions(private val context: Context) {

    private lateinit var pendingIntent: PendingIntent

    private val ioScope by lazy { CoroutineScope(Dispatchers.IO) }

    fun callAlarm(time: String, alarm_code: Int, content: String) {

        val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val receiverIntent = Intent(context, AlarmReceiver::class.java)
        receiverIntent.apply {
            putExtra("alarm_requestCode", alarm_code)
            putExtra("content", content)
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, alarm_code, receiverIntent, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getBroadcast(context, alarm_code, receiverIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd H:mm:ss")
        var dateTime = Date()
        try {
            dateTime = dateFormat.parse(time) as Date
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val calendar = Calendar.getInstance()
        calendar.time = dateTime

        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    
}