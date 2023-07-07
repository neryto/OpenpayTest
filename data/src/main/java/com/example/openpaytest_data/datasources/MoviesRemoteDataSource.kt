package com.example.openpaytest_data.datasources

import com.example.openpaytest_network.responses.MoviesResponse
import com.example.openpaytest_network.responses.NetworkResult
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {
    suspend fun getMovies(type:String) : Flow<NetworkResult<MoviesResponse>>
}