package com.example.openpaytest.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class BaseViewModel : ViewModel() {
    open val _loading : MutableStateFlow<Boolean> = MutableStateFlow(false)
    open val loading : StateFlow<Boolean> get() = _loading

    open fun resetFlowValues(){
        _loading.value = false
    }
}