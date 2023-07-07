package com.example.openpaytest_data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @ColumnInfo(name = "user_id") @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "user_name")
    val name: String,
    @ColumnInfo(name = "user_avatar_path")
    val avatar: String,

    )