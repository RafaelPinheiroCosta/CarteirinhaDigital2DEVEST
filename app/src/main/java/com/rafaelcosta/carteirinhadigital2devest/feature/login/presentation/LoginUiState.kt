package com.rafaelcosta.carteirinhadigital2devest.feature.login.presentation

import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado

data class LoginUiState(
    val usuario: String = "",
    val senha: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val credentialError: Boolean = false,
    val usuarioLogado: UsuarioLogado? = null
) {
    val loginRealizado: Boolean
        get() = usuarioLogado != null
}