package com.example.openpaytest_data.datasources

interface LocationRemoteDataSource {
    suspend fun getLocations()
}