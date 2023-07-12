package com.example.openpaytest_network

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkBuilder {

    fun build(url: String): Retrofit {
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

        val clientLogger : OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(LoggingInterceptor())
            .build()

        val request = Request.Builder()
            .url(url)
            .header("User-Agent", "OkHttp Example")
            .build()
        GlobalScope.launch {
            val response =clientLogger.newCall(request).execute()
            response.body?.close()
        }


        return Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}