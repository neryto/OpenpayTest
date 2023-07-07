package com.example.openpaytest_network.services

import com.example.openpaytest_network.responses.RatedMoviesResponse
import com.example.openpaytest_network.responses.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiServices {
    @GET("account/{accountId}")
   suspend fun getUser(@Path("accountId") accountId : String) :Response<UserResponse>

   @GET("account/{account_id}/rated/movies")
   suspend fun getRatedMovies(@Path("account_id") accountId : String)
   : Response<RatedMoviesResponse>

}