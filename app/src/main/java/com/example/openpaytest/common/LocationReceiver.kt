package com.example.openpaytest.common

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.openpaytest.common.LocationService.Companion.ACTION_SEND_LOCATION
import com.example.openpaytest.common.LocationService.Companion.LOCATION_DATA
import com.example.openpaytest_data.models.LocationItem

class LocationReceiver (private val listener: DataListener) : BroadcastReceiver(){
    interface DataListener {
        fun onDataReceived(data: LocationItem)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onReceive(p0: Context?, intent: Intent?) {
        intent?.let { notNullIntent->
            if (notNullIntent.action == ACTION_SEND_LOCATION) {
                val data = intent.getParcelableExtra<LocationItem>(LOCATION_DATA)
                if (data is LocationItem)
                listener.onDataReceived(data)
            }
        }

    }
}