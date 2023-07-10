package com.example.openpaytest_data.datasources

import android.net.Uri
import com.example.openpaytest_data.DataResult
import kotlinx.coroutines.flow.Flow

interface ImageRemoteDataSource {
    suspend  fun saveImage(uri: Uri) : Flow<DataResult<Boolean>>
}