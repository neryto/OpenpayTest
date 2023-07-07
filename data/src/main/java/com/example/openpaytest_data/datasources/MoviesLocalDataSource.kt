package com.example.openpaytest_data.datasources

import com.example.openpaytest_data.db.entities.MovieEntity

interface MoviesLocalDataSource {

    suspend fun getMovies(type:String):List<MovieEntity>?

    suspend fun insertAllRatedMovies(movies: List<MovieEntity>)
}