package com.example.openpaytest_data.models

enum class MovieType(private val value : Pair<String,String>) {
    PLAYING_NOW(Pair("Paying now","now_playing")),
    MOST_POPULAR(Pair("Most popular","popular")),
    TOP_RATED(Pair("Top rated","top_rated")),
    UPCOMING(Pair("Upcoming","upcoming"));

    override fun toString(): String {
        return value.first
    }

    companion object{
        const val ratedType = "rated"

    }


    fun getType() : String = value.second

}

