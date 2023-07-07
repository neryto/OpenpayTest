package com.example.openpaytest_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.openpaytest_data.db.daos.MoviesDao
import com.example.openpaytest_data.db.daos.UserDao
import com.example.openpaytest_data.db.entities.RatedMovieEntity
import com.example.openpaytest_data.db.entities.UserEntity

@Database(entities = [UserEntity::class,RatedMovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun moviesDao() : MoviesDao
}