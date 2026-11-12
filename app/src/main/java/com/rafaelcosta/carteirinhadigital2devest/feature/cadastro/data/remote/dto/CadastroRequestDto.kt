package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CadastroRequestDto(
    val nome: String,
    val login: String,
    val senha: String,
    val perfil: String,
    val curso: String = ""
)
