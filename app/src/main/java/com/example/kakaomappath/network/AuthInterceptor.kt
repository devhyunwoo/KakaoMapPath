package com.example.kakaomappath.network

import com.example.kakaomappath.Constants.AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", AUTH_TOKEN)
                .build()
        )
    }
}