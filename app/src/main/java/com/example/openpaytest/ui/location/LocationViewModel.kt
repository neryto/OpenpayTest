package com.example.openpaytest.ui.location

import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.models.LocationItem
import com.example.openpaytest_data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationViewModel
@Inject constructor(private val locationRepository: LocationRepository) : BaseViewModel() {

    private val _saveLocationResult : MutableStateFlow<LocationItem?> = MutableStateFlow(null)
    val saveLocationResult : StateFlow<LocationItem?> get() = _saveLocationResult

    suspend fun saveLocation(item: LocationItem) {
        locationRepository.saveLocation(item).collect {
            when (it) {
                DataResult.Loading -> {}
                is DataResult.Success ->{
                    if (it.data)_saveLocationResult.value = item
                }
            }
        }
    }

    override fun resetFlowValues() {
        super.resetFlowValues()
        _saveLocationResult.value = null
    }
}