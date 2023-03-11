package com.posomo.saltit.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request().url
            .newBuilder()
            .build()

        val request = chain.request().newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}