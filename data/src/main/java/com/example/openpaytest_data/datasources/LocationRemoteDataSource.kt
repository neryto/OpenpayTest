package com.example.openpaytest_data.datasources

import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.models.LocationItem
import kotlinx.coroutines.flow.Flow

interface LocationRemoteDataSource {
    suspend fun getLocations() : Flow<DataResult<List<LocationItem>>>
    suspend fun saveLocation(locationItem: LocationItem) : Flow<DataResult<Boolean>>
}