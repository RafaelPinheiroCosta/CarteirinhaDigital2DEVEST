package com.rafaelcosta.carteirinhadigital2devest.app.di

import com.rafaelcosta.carteirinhadigital2devest.core.auth.AuthTokenStore
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository.LoginRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository.UnidadeCurricularRepository

interface AppContainer {

    val loginRepository: LoginRepository

    val unidadeCurricularRepository: UnidadeCurricularRepository

    val authTokenStore: AuthTokenStore
}