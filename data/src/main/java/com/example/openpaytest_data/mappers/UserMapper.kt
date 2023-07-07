package com.example.openpaytest_data.mappers

import com.example.openpaytest_data.db.entities.RatedMovieEntity
import com.example.openpaytest_data.db.entities.UserEntity
import com.example.openpaytest_data.models.RatedMovie
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

    fun toRatedMovie(ratedMoviesResponse: RatedMoviesResponse): List<RatedMovie> {
       return ratedMoviesResponse.results.map { results ->
            RatedMovie(
                id = results.id,
                originalTitle = results.originalTitle,
                posterPath = results.posterPath,
                releaseDate = results.releaseDate,
                title = results.title,
                voteAverage = results.voteAverage
            )
        }
    }

    fun toRatedMovie(ratedMovies: List<RatedMovieEntity>): List<RatedMovie> {
        return ratedMovies.map { results ->
            RatedMovie(
                id = results.id,
                originalTitle = results.originalTitle,
                posterPath = results.posterPath,
                releaseDate = results.releaseDate,
                title = results.title,
                voteAverage = results.voteAverage
            )
        }
    }

    fun toRatedMoviesEntity(ratedMoviesResponse: RatedMoviesResponse): List<RatedMovieEntity> {
       return ratedMoviesResponse.results.map {results ->
            RatedMovieEntity(
                id = results.id,
                originalTitle = results.originalTitle,
                posterPath = results.posterPath,
                releaseDate = results.releaseDate,
                title = results.title,
                voteAverage = results.voteAverage
            )
        }
    }


}