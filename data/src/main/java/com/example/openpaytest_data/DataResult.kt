package com.example.openpaytest_data


sealed class DataResult<out T>{
    data class Success<out T>(val data: T) : DataResult<T>()
    data class  Loading <out T> (val show :Boolean): DataResult<T>()

    data class Error<out T> (val error : String?) : DataResult<T>()
}
