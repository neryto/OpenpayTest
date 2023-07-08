package com.example.openpaytest.di

import android.content.Context
import com.example.openpaytest.common.LocationHandler
import com.example.openpaytest.common.NotificationHandler
import com.example.openpaytest.firestore.FirestoreHandler
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun locationHandleProvider(@ApplicationContext context: Context) : LocationHandler {
       return LocationHandler(context)
    }

    @Provides
    @Singleton
    fun firestoreProvider(@ApplicationContext context: Context) : FirebaseFirestore{
        FirebaseApp.initializeApp(context)
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun firestoreHandlerProvider(
        firestore: FirebaseFirestore,
        notificationHandler: NotificationHandler
    ) : FirestoreHandler{
        return FirestoreHandler(firestore,notificationHandler)
    }

    @Provides
    @Singleton
    fun notificationsHandlerProvider(@ApplicationContext context: Context) : NotificationHandler {
        return NotificationHandler(context)
    }
}