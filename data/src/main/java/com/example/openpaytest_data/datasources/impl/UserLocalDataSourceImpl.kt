package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_data.datasources.UserLocalDataSource
import com.example.openpaytest_data.db.AppDatabase
import com.example.openpaytest_data.db.entities.RatedMovieEntity
import com.example.openpaytest_data.db.entities.UserEntity
import com.example.openpaytest_data.models.RatedMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserLocalDataSourceImpl
@Inject constructor(private val database: AppDatabase) : UserLocalDataSource {

    override suspend fun getUser(): UserEntity? =
        withContext(Dispatchers.IO) {
            database.userDao().getUser()
        }

    override suspend fun saveUser(user: UserEntity) {
        withContext(Dispatchers.IO) {
            database.userDao().saveUser(user)
        }
    }

    override suspend fun getRatedMovies(): List<RatedMovieEntity>?
    = withContext(Dispatchers.IO){
        database.moviesDao().getRatedMoviesDao()
    }



    override suspend fun insertAllRatedMovies(ratedMovies: List<RatedMovieEntity>) {
        withContext(Dispatchers.IO){
            database.moviesDao().insertAllRatedMovies(ratedMovies)
        }
    }

}