package com.example.openpaytest.ui.user

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.models.RatedMovie
import com.example.openpaytest_data.models.User
import com.example.openpaytest_data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(private val userRepository: UserRepository) : BaseViewModel() {

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> get() = _user

    private val _ratedMovies : MutableStateFlow<List<RatedMovie>> = MutableStateFlow(listOf())
    val ratedMovies : StateFlow<List<RatedMovie>> get() = _ratedMovies

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collect {
                when (it) {
                    DataResult.Loading -> _loading.value = true
                    is DataResult.Success -> _user.value = it.data
                }
            }

        }

    }

    fun getRatedMovies() {
        viewModelScope.launch {
            userRepository.getRatedMovies().collect{
                when(it){
                    DataResult.Loading -> _loading.value = true
                    is DataResult.Success -> {
                        _ratedMovies.value = it.data
                    }
                }
            }
        }
    }
}