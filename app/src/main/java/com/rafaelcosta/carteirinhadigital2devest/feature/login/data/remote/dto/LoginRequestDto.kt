package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val login: String,
    val senha: String
)
