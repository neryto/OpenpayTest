package com.example.openpaytest.ui.registerlocation

import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterLocationViewModel
    @Inject constructor(private val locationRepository: LocationRepository)
    : BaseViewModel() {


}