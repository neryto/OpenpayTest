package com.example.openpaytest_data


sealed class DataResult<out T>{
    data class Success<out T>(val data: T) : DataResult<T>()
    object  Loading : DataResult<Nothing>()
}
