package com.example.openpaytest_data.datasources

import com.example.openpaytest_network.responses.NetworkResult
import com.example.openpaytest_network.responses.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun getUser() : Flow<NetworkResult<UserResponse>>
}