package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_data.datasources.LocationRemoteDataSource
import javax.inject.Inject

class LocationRemoteDataSourceImpl
    @Inject constructor(private val fires)
    : LocationRemoteDataSource {
    override suspend fun getLocations() {

    }
}