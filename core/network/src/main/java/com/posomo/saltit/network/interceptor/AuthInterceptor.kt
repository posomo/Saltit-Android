package com.posomo.saltit.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val clientId: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("client_id", clientId)
            .build()

        val request = chain.request().newBuilder().url(httpUrl).build()
        return chain.proceed(request)
    }
}