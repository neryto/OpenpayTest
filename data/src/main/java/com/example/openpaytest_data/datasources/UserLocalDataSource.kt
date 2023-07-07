package com.example.openpaytest_data.datasources

import com.example.openpaytest_data.db.entities.RatedMovieEntity
import com.example.openpaytest_data.db.entities.UserEntity
import com.example.openpaytest_data.models.RatedMovie

interface UserLocalDataSource {
    suspend fun getUser():UserEntity?
    suspend fun saveUser(user:UserEntity)

    suspend fun getRatedMovies():List<RatedMovieEntity>?

    suspend fun insertAllRatedMovies(ratedMovies: List<RatedMovieEntity>)
}