package com.example.openpaytest.firestore

import android.content.Context
import android.location.Location
import android.util.Log
import com.example.openpaytest.R
import com.example.openpaytest.common.NotificationHandler
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class FirestoreHandler @Inject constructor(
    private val firebseFirestore: FirebaseFirestore,
    private val notificationHandler: NotificationHandler,
    private val context: Context
) {


    fun saveLocation(location: Location) {
        val formatter = SimpleDateFormat(context.getString(R.string.date_format))
        val date = Date()
        val current = formatter.format(date)
        val locationData = hashMapOf(
            "latitude" to location.latitude,
            "longitude" to location.longitude,
            "date" to current
        )
        firebseFirestore.collection(context.getString(R.string.collection_path_location_id))
            .add(locationData)
            .addOnSuccessListener {
                notificationHandler.showNotification(
                    context.getString(R.string.notification_title),
                    context.getString(
                        R.string.notification_message,
                        location.latitude.toString(),
                        location.longitude.toString()
                    ))
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }

    }
}