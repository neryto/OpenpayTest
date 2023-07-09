package com.example.openpaytest_data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationItem(
    val latitude:String,
    val longitude:String
):Parcelable