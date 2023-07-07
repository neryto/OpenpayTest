package com.example.openpaytest_network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {


    fun build(url: String): Retrofit {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}