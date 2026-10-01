package com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model

data class UsuarioLogado(
    val id: String,
    val nome: String,
    val matricula: String,
    val curso: String,
    val turma: String,
    val token: String
)