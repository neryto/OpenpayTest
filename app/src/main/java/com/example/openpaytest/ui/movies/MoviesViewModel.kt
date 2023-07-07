package com.example.openpaytest.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
    @Inject constructor(private val moviesRepository: MoviesRepository)
    : ViewModel() {

    private val _movies : MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
    val movies : StateFlow<List<Movie>> get() = _movies

    fun getMovies(type: String) {
        viewModelScope.launch {
            moviesRepository.getMovies(type).collect{
                when(it){
                    DataResult.Loading -> {}
                    is DataResult.Success -> _movies.value = it.data
                }
            }
        }
    }

}