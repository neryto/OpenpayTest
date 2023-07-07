package com.example.openpaytest.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url : String){
   Glide.with(this.context)
       .load(url)
       .into(this)
}