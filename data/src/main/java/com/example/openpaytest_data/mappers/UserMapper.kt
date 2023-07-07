package com.example.openpaytest_data.mappers

import com.example.openpaytest_data.db.entities.MovieEntity
import com.example.openpaytest_data.db.entities.UserEntity
import com.example.openpaytest_data.models.Movie
import com.example.openpaytest_data.models.MovieType
import com.example.openpaytest_data.models.User
import com.example.openpaytest_network.responses.RatedMoviesResponse
import com.example.openpaytest_network.responses.UserResponse
import javax.inject.Inject

class UserMapper
@Inject
constructor() {

    fun toUserDomain(userResponse: UserResponse): User {
        val avatarResponse = userResponse.avatar
        val tmdbResponse = avatarResponse.tmdb
        return User(
            avatarUrl = tmdbResponse.avatarPath,
            name = userResponse.name
        )
    }

    fun toUserDomain(userEntity: UserEntity) :User{
        return User(
            avatarUrl = userEntity.avatar,
            name = userEntity.name
        )
    }

    fun toUserEntity(user: UserResponse): UserEntity {
        return UserEntity(
            id = user.id,
            name = user.name,
            avatar = user.avatar.tmdb.avatarPath
        )
    }

    fun toRatedMovie(ratedMoviesResponse: RatedMoviesResponse): List<Movie> {
       return ratedMoviesResponse.results.map { result ->
            Movie(
                id = result.id.toString(),
                originalTitle = result.originalTitle,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                voteAverage = result.voteAverage
            )
        }
    }

    fun toRatedMovie(ratedMovies: List<MovieEntity>): List<Movie> {
        return ratedMovies.map { result ->
            Movie(
                id = result.id,
                originalTitle = result.originalTitle,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                voteAverage = result.voteAverage
            )
        }
    }

    fun toRatedMoviesEntity(ratedMoviesResponse: RatedMoviesResponse): List<MovieEntity> {
       return ratedMoviesResponse.results.map {result ->
            MovieEntity(
                id = "${result.id}_${MovieType.ratedType}",
                originalTitle = result.originalTitle,
                posterPath = result.posterPath,
                releaseDate = result.releaseDate,
                title = result.title,
                voteAverage = result.voteAverage,
                type = MovieType.ratedType
            )
        }
    }


}