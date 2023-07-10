package com.example.openpaytest.ui.user

import androidx.lifecycle.viewModelScope
import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_data.models.User
import com.example.openpaytest_data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(private val userRepository: UserRepository) : BaseViewModel() {

    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> get() = _user

    private val _ratedMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val ratedMovies: StateFlow<List<Movie>> get() = _ratedMovies

    fun getUser() {
        viewModelScope.launch {
            userRepository.getUser().collect {
                when (it) {
                    is DataResult.Loading -> _loading.value = true
                    is DataResult.Success -> _user.value = it.data
                    is DataResult.Error -> {_error.value=it.error}
                }
            }

        }

    }

    fun getRatedMovies() {
        viewModelScope.launch {
            userRepository.getRatedMovies().collect {
                when (it) {
                    is DataResult.Loading -> _loading.value = true
                    is DataResult.Success -> _ratedMovies.value = it.data
                    is DataResult.Error -> _error.value = it.error
                }
            }
        }
    }
}