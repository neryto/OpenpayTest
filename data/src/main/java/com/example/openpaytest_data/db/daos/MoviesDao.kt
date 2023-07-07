package com.example.openpaytest_data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openpaytest_data.db.entities.RatedMovieEntity

@Dao
interface MoviesDao {
    @Query("SELECT * FROM RatedMovieEntity")
    fun getRatedMoviesDao(): List<RatedMovieEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRatedMovies(ratedMovies: List<RatedMovieEntity>)

}