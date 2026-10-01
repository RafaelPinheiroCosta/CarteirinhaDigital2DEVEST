package com.rafaelcosta.carteirinhadigital2devest.core.auth

class SessionTokenStore {

    @Volatile
    private var token: String? = null

    fun salvar(token: String) {
        this.token = token
    }

    fun obter(): String? {
        return token
    }

    fun limpar() {
        token = null
    }
}