package com.example.openpaytest_data.firestore

import android.content.Context
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.R
import com.example.openpaytest_data.models.LocationItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class FirestoreHandler @Inject constructor(
    private val firebseFirestore: FirebaseFirestore,
    private val context: Context
) {


    fun saveLocation(location: LocationItem) : Flow<DataResult<Boolean>>  = flow{
        val formatter = SimpleDateFormat(context.getString(R.string.date_format))
        val date = Date()
        val current = formatter.format(date)
        val locationData = hashMapOf(
            "latitude" to location.latitude,
            "longitude" to location.longitude,
            "date" to current
        )
        try {
            firebseFirestore.collection(context.getString(R.string.collection_path_location_id))
                .add(locationData)
                .await()
            emit(DataResult.Success(true))
        }catch (e:Exception){
            e.printStackTrace()
        }

    }

    fun getLocations() : Flow<DataResult<List<LocationItem>>> = flow {
      val result =   firebseFirestore
            .collection(context.getString(R.string.collection_path_location_id))
            .get()
            .await()
        emit(DataResult.Success(
            result.map {document->
                val data = document.data
                LocationItem(
                    latitude = data["latitude"] as String,
                    longitude = data["longitude"] as String,
                    date = data["date"] as String
                )
            }
        ))


    }
}