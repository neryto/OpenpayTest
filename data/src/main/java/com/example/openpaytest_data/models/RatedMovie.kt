package com.example.openpaytest_data.models


data class RatedMovie(
    val id: Int,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double
)
