package com.example.catwork.presentation.home.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class AlarmReceiver(): BroadcastReceiver() {

    private lateinit var manager: NotificationManager
    private lateinit var builder: NotificationCompat.Builder

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        manager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
        )

        builder = NotificationCompat.Builder(context, CHANNEL_ID)

        val intent2 = Intent(context, AlarmService::class.java)
        val requestCode = intent?.extras!!.getInt("alarm_requestCode")
        val title = intent.extras!!.getString("content")

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context, requestCode, intent2, PendingIntent.FLAG_IMMUTABLE)
        } else {
            PendingIntent.getActivity(context, requestCode, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = builder.setContentTitle(title)
            .setContentText("CatWork")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        manager.notify(1, notification)
    }


}