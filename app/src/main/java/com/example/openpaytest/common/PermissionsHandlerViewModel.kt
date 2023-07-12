package com.example.openpaytest.common

import com.example.openpaytest.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PermissionsHandlerViewModel
    @Inject constructor()
    : BaseViewModel() {

    private val _permissionsGranted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val permissionsGranted: StateFlow<Boolean> get() = _permissionsGranted

    private val _isReady : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isReady : StateFlow<Boolean> get() = _isReady

    private val _launchPermissionsRequest: MutableStateFlow<Pair<String,Boolean>> =
        MutableStateFlow(
        Pair("",false)
    )
    val launchPermissionsRequest: StateFlow<Pair<String,Boolean>> get() = _launchPermissionsRequest

    fun updatePermissionValue(status : Boolean){
        _permissionsGranted.value = status
    }

    fun updateLaunchPermissionRequestValue(permission:String,status: Boolean){
        _launchPermissionsRequest.value = Pair(permission,status)
    }

   suspend fun performSplash() {
        delay(2000)
        _isReady.value = true
    }

}