package com.example.openpaytest_network.responses

sealed class NetworkResult<out T>{
    data class Success<out T>(val data: T) : NetworkResult<T>()
    object  Loading : NetworkResult<Nothing>()
    class Error<out T>(val code: Int, val message: String?) : NetworkResult<T>()
    class Exception<out T>(val e: Throwable) : NetworkResult<T>()
}




