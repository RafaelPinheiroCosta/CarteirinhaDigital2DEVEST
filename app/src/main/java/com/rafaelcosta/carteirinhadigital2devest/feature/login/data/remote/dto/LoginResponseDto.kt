package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: String,
    val nome: String,
    val matricula: String,
    val curso: String,
    val turma: String,
    val token: String
)
