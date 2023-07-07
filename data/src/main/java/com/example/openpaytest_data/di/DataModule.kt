package com.example.openpaytest_data.di

import android.content.Context
import androidx.room.Room
import com.example.openpaytest_data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun databaseProvider( @ApplicationContext context: Context) : AppDatabase
            = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "app_database"
    ).build()
}