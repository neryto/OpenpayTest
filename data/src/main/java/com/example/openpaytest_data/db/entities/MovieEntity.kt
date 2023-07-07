package com.example.openpaytest_data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @ColumnInfo(name = "rated_movie_id") @PrimaryKey
    val id: String,
    @ColumnInfo(name = "rated_movie_original_title")
    val originalTitle: String,
    @ColumnInfo(name = "rated_movie_poster_path")
    val posterPath: String,
    @ColumnInfo(name = "rated_movie_release_date")
    val releaseDate: String,
    @ColumnInfo(name = "rated_movie_title")
    val title: String,
    @ColumnInfo(name = "rated_vote_average")
    val voteAverage: Double,
    val type:String,
    val overview: String = "",
    )