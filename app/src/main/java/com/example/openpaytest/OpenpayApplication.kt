package com.example.openpaytest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpenpayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationsChanel()
    }

    private fun createNotificationsChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.notification_chanel)
            val channelName = getString(R.string.app_name)
            val channelDescription = getString(R.string.notification_chanel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH

            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}