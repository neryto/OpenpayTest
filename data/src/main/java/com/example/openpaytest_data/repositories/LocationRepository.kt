package com.example.openpaytest_data.repositories

import com.example.openpaytest_data.datasources.impl.LocationDataSourceImpl
import javax.inject.Inject

class LocationRepository
@Inject constructor(private val locationDataSource: LocationDataSourceImpl) {
    fun registerLocations() {
        locationDataSource.registerLocations()
    }
}