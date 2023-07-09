package com.example.openpaytest.di

import android.content.Context
import com.example.openpaytest.common.LocationHandler
import com.example.openpaytest.common.NotificationHandler
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
    fun locationHandleProvider(@ApplicationContext context: Context): LocationHandler {
        return LocationHandler(context)
    }

    @Provides
    @Singleton
    fun notificationsHandlerProvider(@ApplicationContext context: Context): NotificationHandler {
        return NotificationHandler(context)
    }
}