package com.example.openpaytest_data.repositories

import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.impl.UserDataSourceImpl
import com.example.openpaytest_data.mappers.UserMapper
import com.example.openpaytest_data.models.User
import com.example.openpaytest_network.responses.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository
@Inject
constructor(
    private val userRemoteDataSource: UserDataSourceImpl,
    private val userMapper: UserMapper
    ) {
    suspend fun getUser(): Flow<DataResult<User>>  = flow {
        userRemoteDataSource.getUser().collect{
            when(it){
                is NetworkResult.Error -> {//call local data source
                     }
                is NetworkResult.Exception -> {
                    //Call local data source
                }
                NetworkResult.Loading -> emit(DataResult.Loading)
                is NetworkResult.Success ->
                    emit(DataResult.Success(userMapper.toUserDomain(it.data)))
            }
        }
    }



}