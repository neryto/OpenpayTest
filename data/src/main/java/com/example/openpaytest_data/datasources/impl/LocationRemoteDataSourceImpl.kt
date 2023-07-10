package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.LocationRemoteDataSource
import com.example.openpaytest_data.firestore.FirestoreHandler
import com.example.openpaytest_data.models.LocationItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRemoteDataSourceImpl
@Inject constructor(private val firestoreHandler: FirestoreHandler) : LocationRemoteDataSource {
    override suspend fun getLocations(): Flow<DataResult<List<LocationItem>>> =
        firestoreHandler.getLocations()


    override suspend fun saveLocation(locationItem: LocationItem): Flow<DataResult<Boolean>> =
        firestoreHandler.saveLocation(locationItem)


}