package com.example.openpaytest.ui.map

import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.models.LocationItem
import com.example.openpaytest_data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapViewModel
@Inject constructor(private val locationRepository: LocationRepository) : BaseViewModel() {

    private val _locations: MutableStateFlow<List<LocationItem>?> = MutableStateFlow(null)
    val locations: StateFlow<List<LocationItem>?> get() = _locations
    suspend fun getLocations() {
        locationRepository.getLocations().collect {
            when (it) {
                is DataResult.Loading -> {}
                is DataResult.Success -> _locations.value = it.data
                is DataResult.Error -> {}
            }
        }
    }

    override fun resetFlowValues() {
        super.resetFlowValues()
        _locations.value = null
    }
}