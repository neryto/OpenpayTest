package com.example.openpaytest_data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openpaytest_data.db.entities.MovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM MovieEntity WHERE type = :movieType")
    fun getRatedMoviesDao(movieType:String): List<MovieEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRatedMovies(ratedMovies: List<MovieEntity>)

}