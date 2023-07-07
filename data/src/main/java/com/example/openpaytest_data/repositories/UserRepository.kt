package com.example.openpaytest_data.repositories

import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.impl.UserLocalDataSourceImpl
import com.example.openpaytest_data.datasources.impl.UserRemoteDataSourceImpl
import com.example.openpaytest_data.mappers.UserMapper
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_data.models.User
import com.example.openpaytest_network.responses.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository
@Inject
constructor(
    private val userRemoteDataSource: UserRemoteDataSourceImpl,
    private val userLocalDataSource: UserLocalDataSourceImpl,
    private val userMapper: UserMapper
) {
    suspend fun getUser(): Flow<DataResult<User>> = flow {
        userRemoteDataSource.getUser().collect {
            when (it) {
                is NetworkResult.Error -> {
                    //call local data source
                    userLocalDataSource.getUser()?.let { userEntity ->
                        emit(DataResult.Success(userMapper.toUserDomain(userEntity)))
                    }
                    //TODO handle errors

                }

                is NetworkResult.Exception -> {
                    //Call local data source
                    userLocalDataSource.getUser()?.let { userEntity ->
                        emit(DataResult.Success(userMapper.toUserDomain(userEntity)))
                    }
                    //TODO handle errors
                }

                NetworkResult.Loading -> emit(DataResult.Loading)
                is NetworkResult.Success -> {
                    emit(DataResult.Success(userMapper.toUserDomain(it.data)))
                    //save user into local db
                    userLocalDataSource.saveUser(userMapper.toUserEntity(it.data))
                }
            }
        }
    }

   suspend fun getRatedMovies() : Flow<DataResult<List<Movie>>> = flow{
        userRemoteDataSource.getRatedMovies().collect{
            when(it){
                is NetworkResult.Error -> {
                    userLocalDataSource.getRatedMovies()?.let {ratedMovies->
                        emit(DataResult.Success(userMapper.toRatedMovie(ratedMovies)))
                    }
                }
                is NetworkResult.Exception -> {
                    userLocalDataSource.getRatedMovies()?.let {ratedMovies->
                        emit(DataResult.Success(userMapper.toRatedMovie(ratedMovies)))
                    }
                }
                NetworkResult.Loading -> {
                    emit(DataResult.Loading)
                }
                is NetworkResult.Success -> {
                    emit(DataResult.Success(userMapper.toRatedMovie(it.data)))
                    userLocalDataSource.insertAllRatedMovies(userMapper.toRatedMoviesEntity(it.data))
                }
            }
        }
    }


}