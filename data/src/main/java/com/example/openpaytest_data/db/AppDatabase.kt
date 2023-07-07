package com.example.openpaytest_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openpaytest_data.db.daos.UserDao
import com.example.openpaytest_data.db.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}