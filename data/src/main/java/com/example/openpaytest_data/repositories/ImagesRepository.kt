package com.example.openpaytest_data.repositories

import android.net.Uri
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.impl.ImageRemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val imageRemoteDataSource: ImageRemoteDataSourceImpl
) {
    suspend fun saveImage(uri: Uri) : Flow<DataResult<Boolean>>  =
        imageRemoteDataSource.saveImage(uri)


}