package com.rafaelcosta.carteirinhadigital2devest.core.network

import com.rafaelcosta.carteirinhadigital2devest.core.auth.SessionTokenStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sessionTokenStore: SessionTokenStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestOriginal = chain.request()
        val token = sessionTokenStore.obter()

        if (token.isNullOrBlank()) {
            return chain.proceed(requestOriginal)
        }

        val requestAutenticada = requestOriginal.newBuilder()
                .header(
                    "Authorization",
                    "Bearer $token"
                ).build()

        return chain.proceed(requestAutenticada)
    }
}