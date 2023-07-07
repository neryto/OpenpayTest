package com.example.openpaytest_data.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.openpaytest_data.db.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity LIMIT 1")
    fun getUser() : UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userEntity: UserEntity)

}