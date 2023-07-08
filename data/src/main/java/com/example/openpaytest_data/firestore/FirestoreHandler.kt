package com.example.openpaytest_data.firestore

import android.location.Location
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class FirestoreHandler @Inject constructor(
    private val firebseFirestore: FirebaseFirestore
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
                Log.e("MYLOCATION", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("MYLOCATION", "Error adding document", e)
            }

    }
}