package com.example.openpaytest_data.datasources.impl

import android.net.Uri
import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.ImageRemoteDataSource
import com.example.openpaytest_data.firebase.FirebaseStorageHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRemoteDataSourceImpl
@Inject constructor(private val firebaseStorageHandler: FirebaseStorageHandler)
    : ImageRemoteDataSource {
    override suspend fun saveImage(uri: Uri): Flow<DataResult<Boolean>>  =
      firebaseStorageHandler.saveImage(uri)

}