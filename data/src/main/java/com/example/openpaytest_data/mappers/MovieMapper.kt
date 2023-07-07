package com.example.openpaytest_data.mappers

import com.example.openpaytest_data.db.entities.MovieEntity
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_network.responses.MoviesResponse
import javax.inject.Inject

class MovieMapper @Inject constructor() {

    fun toMovie(moviesResponse: MoviesResponse): List<Movie> {
        return moviesResponse.results.map { result ->
            Movie(
                id = result.id.toString(),
                originalTitle = result.originalTitle,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                voteAverage = result.voteAverage,
                overview = result.overview
            )
        }
    }

    fun toMovie(moviesEntity: List<MovieEntity>): List<Movie> {
        return moviesEntity.map { result ->
            Movie(
                id = result.id,
                originalTitle = result.originalTitle,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                voteAverage = result.voteAverage,
                overview = result.overview
            )
        }
    }

    fun toMoviesEntity(type: String, data: MoviesResponse): List<MovieEntity> {
        return data.results.map { result ->
            MovieEntity(
                id = "${result.id}_${type}",
                originalTitle = result.originalTitle,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                voteAverage = result.voteAverage,
                type = type,
                overview = result.overview
            )
        }
    }
}