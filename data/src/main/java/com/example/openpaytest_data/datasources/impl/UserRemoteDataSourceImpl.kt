package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_network.BuildConfig
import com.example.openpaytest_data.datasources.UserRemoteDataSource
import com.example.openpaytest_network.interactors.NetworkInteractor
import com.example.openpaytest_network.responses.NetworkResult
import com.example.openpaytest_network.responses.UserResponse
import com.example.openpaytest_network.services.UserApiServices
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRemoteDataSourceImpl
    @Inject
    constructor(
        private val networkInteractor: NetworkInteractor,
        private val userApiServices: UserApiServices
        )
    : UserRemoteDataSource {
    override suspend fun getUser() : Flow< NetworkResult<UserResponse>> {
    return  networkInteractor.handleApi {
          userApiServices.getUser(BuildConfig.ACCOUNT_ID)
      }
    }


}