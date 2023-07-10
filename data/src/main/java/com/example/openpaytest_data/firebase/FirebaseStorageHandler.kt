package com.example.openpaytest_data.firebase

import android.net.Uri
import android.util.Log
import com.example.openpaytest_data.DataResult
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject

class FirebaseStorageHandler @Inject constructor() {
    private val storageRef = FirebaseStorage.getInstance().reference

   suspend fun saveImage(uri: Uri) : Flow<DataResult<Boolean>> = flow{
       emit(DataResult.Loading(true))
       val imageRef = storageRef.child("images/${UUID.randomUUID()}.jpg")
       try {
           imageRef.putFile(uri).await()
           emit(DataResult.Loading(false))
           emit(DataResult.Success(true))

       }catch (e:Exception){
           e.printStackTrace()
           emit(DataResult.Loading(false))
       }
   }

}