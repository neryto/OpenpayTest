package com.example.openpaytest_data.datasources.impl

import com.example.openpaytest_data.datasources.MoviesLocalDataSource
import com.example.openpaytest_data.db.AppDatabase
import com.example.openpaytest_data.db.entities.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesLocalDataSourceImpl
@Inject constructor(private val database: AppDatabase) : MoviesLocalDataSource {
    override suspend fun getMovies(type: String): List<MovieEntity>? = withContext(Dispatchers.IO) {
        database.moviesDao().getRatedMoviesDao(type)
    }


    override suspend fun insertAllRatedMovies(movies: List<MovieEntity>) {
        withContext(Dispatchers.IO) {
            database.moviesDao().insertAllRatedMovies(movies)
        }
    }
}