package com.rafaelcosta.carteirinhadigital2devest.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rafaelcosta.carteirinhadigital2devest.core.auth.SessionTokenStore
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkClient(
    private val baseUrl: String,
    private val sessionTokenStore: SessionTokenStore
) {
    private val json = Json { ignoreUnknownKeys = true }
    private fun createLoggingInterceptor(): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {
            redactHeader("Authorization")
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    private val publicOkHttpClient:OkHttpClient by lazy {

        OkHttpClient.Builder()
            .addInterceptor(
                createLoggingInterceptor()
            ).build()
    }
    private val authenticatedOkHttpClient:OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(
                AuthInterceptor(sessionTokenStore = sessionTokenStore)
            )
            .addInterceptor(
                createLoggingInterceptor()
            ).build()
    }

    private fun createRetrofit(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            ).build()
    }
    fun <T : Any> createPublic(serviceClass: Class<T>): T {
        return createRetrofit(publicOkHttpClient).create(serviceClass)
    }
    fun <T : Any> createAuthenticated(serviceClass: Class<T>): T {
        return createRetrofit(authenticatedOkHttpClient).create(serviceClass)
    }
}
