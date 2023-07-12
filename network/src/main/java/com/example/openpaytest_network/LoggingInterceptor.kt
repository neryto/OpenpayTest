package com.example.openpaytest_network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.internal.concurrent.TaskRunner.Companion.logger

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val t1 : Long = System.nanoTime()
        logger.info(String.format("Sending request %s on %s%n%s",
            request.url,chain.connection(),request.headers
            ))


        val t2 : Long = System.nanoTime()
        val response  = chain.proceed(request)
        logger.info(String.format("Received response for %s in $.1fms%n%s",
            response.request.url,(t2-t1)/ 1e6.toDouble() ,response.headers
            ))
        return response
    }
}