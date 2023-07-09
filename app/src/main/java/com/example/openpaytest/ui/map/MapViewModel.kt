package com.example.openpaytest.ui.map

import android.util.Log
import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel
    @Inject constructor(private val locationRepository: LocationRepository)
    : BaseViewModel() {
       suspend fun getLocations(){
            locationRepository.getLocations()
        }
}