package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_data.datasources.UserLocalDataSource
import com.example.openpaytest_data.db.AppDatabase
import com.example.openpaytest_data.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl
@Inject constructor(private val database: AppDatabase) : UserLocalDataSource {

    override suspend fun getUser(): UserEntity? =
        withContext(Dispatchers.IO) {
            database.userDao().getUser()
        }

    override suspend fun saveUser(user: UserEntity) {
        withContext(Dispatchers.IO) {
            database.userDao().saveUser(user)
        }
    }

}