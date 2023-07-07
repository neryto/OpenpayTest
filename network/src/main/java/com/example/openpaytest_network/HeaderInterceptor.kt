package com.example.openpaytest_network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.AP1_K3Y}" )
            .header("Accept", "application/json")
            .build()
        return chain.proceed(modifiedRequest)
    }
}