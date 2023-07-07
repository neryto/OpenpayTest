package com.example.openpaytest_data.mappers

import com.example.openpaytest_data.models.User
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


}