package com.rafaelcosta.carteirinhadigital2devest.core.network

import com.rafaelcosta.carteirinhadigital2devest.core.auth.AuthTokenStore
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val tokenStore: AuthTokenStore
) : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {

        val originalRequest = chain.request()

        val token = tokenStore.getToken()

        if (token.isNullOrBlank()) {
            return chain.proceed(originalRequest)
        }

        val authenticatedRequest =
            originalRequest
                .newBuilder()
                .header(
                    "Authorization",
                    "Bearer $token"
                )
                .build()

        return chain.proceed(
            authenticatedRequest
        )
    }
}