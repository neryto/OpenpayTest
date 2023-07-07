package com.example.openpaytest_network.responses

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val avatar : Avatar,
    val id : Int,
    val name : String,
    @SerializedName("username")
    val userName : String
)

data class Avatar(
    val tmdb : Tmdb
)

data class Tmdb(
    @SerializedName("avatar_path")
    val avatarPath : String
)
