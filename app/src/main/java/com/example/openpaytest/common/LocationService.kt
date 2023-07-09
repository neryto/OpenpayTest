package com.example.openpaytest.common

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.openpaytest_data.models.LocationItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    companion object{
        const val ACTION_SEND_LOCATION = "ACTION_SEND_LOCATION"
        const val LOCATION_DATA = "LOCATION_DATA"
    }

    @Inject
    lateinit var locationHandler: LocationHandler

    private lateinit var scheduledExecutor: ScheduledExecutorService


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor()
        scheduledExecutor.scheduleAtFixedRate({
            locationHandler.getCurrentLocation {
                val data = LocationItem(
                    latitude = it.latitude.toString(),
                    longitude = it.longitude.toString()
                )
             Intent(ACTION_SEND_LOCATION).apply {
                  putExtra(LOCATION_DATA, data)
                  LocalBroadcastManager
                      .getInstance(this@LocationService)
                      .sendBroadcast(this)

              }
            }
        }, 0, 5, TimeUnit.MINUTES)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scheduledExecutor.shutdown()
    }
}