package com.example.openpaytest.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel
    @Inject
    constructor()
    : ViewModel() {

    private val _text : MutableStateFlow<String>  = MutableStateFlow("This is home Fragment")

    val text: StateFlow<String> get()  = _text
}