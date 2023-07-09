package com.example.openpaytest_data.repositories

import com.example.openpaytest_data.datasources.LocationRemoteDataSource
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSource
    ) {
    suspend fun getLocations(){
        locationRemoteDataSource.getLocations()
    }
}