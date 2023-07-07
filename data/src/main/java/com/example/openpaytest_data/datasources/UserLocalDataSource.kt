package com.example.openpaytest_data.datasources

import com.example.openpaytest_data.db.entities.UserEntity

interface UserLocalDataSource {
    suspend fun getUser():UserEntity?
    suspend fun saveUser(user:UserEntity)
}