package com.rafaelcosta.carteirinhadigital2devest.app.di

import com.rafaelcosta.carteirinhadigital2devest.core.auth.SessionTokenStore
import com.rafaelcosta.carteirinhadigital2devest.core.network.NetworkClient
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.service.AuthApi
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.ApiLoginRepositoryImpl
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.FakeLoginRepositoryImpl
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.LoginRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data.remote.service.UnidadeCurricularApi
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data.repository.ApiUnidadeCurricularRepositoryImpl
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

class DefaultAppContainer : AppContainer {
    override val sessionTokenStore : SessionTokenStore = SessionTokenStore()
    private val networkClient =
        NetworkClient(
            baseUrl = BASE_URL,
            sessionTokenStore = sessionTokenStore
        )

    private val authApi : AuthApi by lazy {
        networkClient.createPublic(
            AuthApi::class.java
        )
    }
    private val unidadeCurricularApi : UnidadeCurricularApi by lazy {
        networkClient.createAuthenticated(
                UnidadeCurricularApi::class.java
            )
    }
    override val loginRepository : LoginRepository by lazy {
        if (USE_FAKE_LOGIN_REPOSITORY ) {
            FakeLoginRepositoryImpl()
        } else {
            ApiLoginRepositoryImpl(
                api =authApi
            )
        }
    }
    override val unidadeCurricularRepository : UnidadeCurricularRepository by lazy {
        ApiUnidadeCurricularRepositoryImpl(
            api = unidadeCurricularApi
        )
    }
    companion object {
        private const val BASE_URL = "http://10.0.2.2:8080/"
        private const val USE_FAKE_LOGIN_REPOSITORY = false
    }
}
