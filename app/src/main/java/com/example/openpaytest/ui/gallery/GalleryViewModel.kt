package com.example.openpaytest.ui.gallery

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.openpaytest.base.BaseViewModel
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.repositories.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel
@Inject constructor(private val imagesRepository: ImagesRepository) : BaseViewModel() {

    private val _saveImageResult: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val saveImageResult: StateFlow<Boolean> get() = _saveImageResult

    suspend fun saveImage(uri: Uri) {
        viewModelScope.launch {
            imagesRepository.saveImage(uri).collect {
                when (it) {
                    is DataResult.Loading -> {_loading.value = it.show}
                    is DataResult.Success -> _saveImageResult.value = it.data
                    is DataResult.Error -> {}
                }
            }
        }
    }

    override fun resetFlowValues() {
        super.resetFlowValues()
        _saveImageResult.value = false
    }

}