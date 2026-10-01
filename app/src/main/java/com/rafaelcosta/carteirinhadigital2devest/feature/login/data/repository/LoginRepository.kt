package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado

interface LoginRepository {
    suspend fun login( usuario:String, senha:String): Result<UsuarioLogado>
}