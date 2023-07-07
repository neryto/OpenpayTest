package com.example.openpaytest_network.services

import com.example.openpaytest_network.responses.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiServices {
    @GET("movie/{movieType}")
    suspend fun getMovies(@Path("movieType") movieType : String) : Response<MoviesResponse>
}