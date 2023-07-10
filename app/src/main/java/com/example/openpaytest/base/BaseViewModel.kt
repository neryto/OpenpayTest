package com.example.openpaytest.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel() {
    protected val _loading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    open val loading : StateFlow<Boolean> get() = _loading

    protected val _error : MutableStateFlow<String?> = MutableStateFlow(null)
    open val error : StateFlow<String?> get() = _error

    open fun resetFlowValues(){
        _loading.value = false
        _error.value = null
    }
}