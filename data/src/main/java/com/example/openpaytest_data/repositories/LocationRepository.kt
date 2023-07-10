package com.example.openpaytest_data.repositories

import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.impl.LocationRemoteDataSourceImpl
import com.example.openpaytest_data.models.LocationItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationRemoteDataSource: LocationRemoteDataSourceImpl
) {
    suspend fun getLocations() : Flow<DataResult<List<LocationItem>>> =
        locationRemoteDataSource.getLocations()


    suspend fun saveLocation(locationItem:LocationItem) : Flow<DataResult<Boolean>> =
        locationRemoteDataSource.saveLocation(locationItem)

}