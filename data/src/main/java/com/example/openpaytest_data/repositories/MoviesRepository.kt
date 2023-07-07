package com.example.openpaytest_data.repositories

import com.example.openpaytest_data.DataResult
import com.example.openpaytest_data.datasources.impl.MoviesLocalDataSourceImpl
import com.example.openpaytest_data.datasources.impl.MoviesRemoteDataSourceImpl
import com.example.openpaytest_data.mappers.MovieMapper
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_network.responses.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepository
@Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSourceImpl,
    private val moviesLocalDataSource: MoviesLocalDataSourceImpl,
    private val movieMapper: MovieMapper
) {

    suspend fun getMovies(type: String): Flow<DataResult<List<Movie>>> = flow {
        moviesRemoteDataSource.getMovies(type).collect {
            when (it) {
                is NetworkResult.Error -> {
                    moviesLocalDataSource.getMovies(type)?.let {movies->
                        emit(DataResult.Success(movieMapper.toMovie(movies)))
                    }
                }

                is NetworkResult.Exception -> {
                    moviesLocalDataSource.getMovies(type)?.let {movies->
                        emit(DataResult.Success(movieMapper.toMovie(movies)))
                    }
                }

                NetworkResult.Loading -> {
                    emit(DataResult.Loading)
                }

                is NetworkResult.Success -> {
                    emit(DataResult.Success(movieMapper.toMovie(it.data)))
                    moviesLocalDataSource
                        .insertAllRatedMovies(
                            movieMapper.toMoviesEntity(type, it.data)
                        )
                }
            }
        }
    }
}