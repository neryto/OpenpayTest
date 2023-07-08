package com.example.openpaytest.firestore

import android.location.Location
import android.util.Log
import com.example.openpaytest.common.NotificationHandler
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class FirestoreHandler @Inject constructor(
    private val firebseFirestore: FirebaseFirestore,
    private val notificationHandler: NotificationHandler
) {


    fun saveLocation(location: Location) {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val date = Date()
        val current = formatter.format(date)
        val locationData = hashMapOf(
            "latitude" to location.latitude,
            "longitude" to location.longitude,
            "date" to current
        )
        firebseFirestore.collection("location")
            .add(locationData)
            .addOnSuccessListener { documentReference ->
                notificationHandler.showNotification(
                    "Location registered",
                    "location successfully registered in Firestore ${location.latitude},${location.longitude}")
            }
            .addOnFailureListener { e ->
                Log.w("MYLOCATION", "Error adding document", e)
            }

    }
}