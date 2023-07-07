package com.example.openpaytest_data.models


data class Movie(
    val id: String,
    val originalTitle: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val overview: String? = null,
    )
