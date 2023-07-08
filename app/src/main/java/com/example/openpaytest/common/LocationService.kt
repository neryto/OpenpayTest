package com.example.openpaytest.common

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.openpaytest.firestore.FirestoreHandler
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    @Inject
    lateinit var locationHandler: LocationHandler

    @Inject
    lateinit var firestoreHandler: FirestoreHandler



    private lateinit var scheduledExecutor: ScheduledExecutorService


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        scheduledExecutor = Executors.newSingleThreadScheduledExecutor()
        scheduledExecutor.scheduleAtFixedRate({
            locationHandler.getCurrentLocation {
                firestoreHandler.saveLocation(it)
            }
        }, 0, 5, TimeUnit.MINUTES)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scheduledExecutor.shutdown()
    }
}