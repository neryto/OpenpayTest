package com.example.openpaytest_network.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    val dates: Dates,
    val page: Int,
    var results: List<Results> = arrayListOf(),
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class Dates(
    val maximum: String,
    val minimum: String
)
