package com.example.openpaytest.common

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.openpaytest.R
import javax.inject.Inject


class NotificationHandler @Inject constructor(private val context: Context) {

     fun showNotification(title: String, message: String) {
        val builder = NotificationCompat.Builder(
            context,
            context.getString(R.string.notification_chanel))
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_MAX)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        notificationManager.notify(1, builder.build())
    }

}