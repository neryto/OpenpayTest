package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_data.datasources.MoviesRemoteDataSource
import com.example.openpaytest_network.interactors.NetworkInteractor
import com.example.openpaytest_network.responses.MoviesResponse
import com.example.openpaytest_network.responses.NetworkResult
import com.example.openpaytest_network.services.MoviesApiServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRemoteDataSourceImpl
@Inject constructor(
    private val networkInteractor: NetworkInteractor,
    private val moviesApiServices: MoviesApiServices
) : MoviesRemoteDataSource {
    override suspend fun getMovies(type:String): Flow<NetworkResult<MoviesResponse>> =
        networkInteractor.handleApi {
            moviesApiServices.getMovies(type)

        }
}