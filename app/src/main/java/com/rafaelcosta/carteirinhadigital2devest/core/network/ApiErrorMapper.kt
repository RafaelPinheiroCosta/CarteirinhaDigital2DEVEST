package com.rafaelcosta.carteirinhadigital2devest.core.network

import retrofit2.HttpException
import java.io.IOException

fun apiErrorMessage(error: Throwable): String = when (error) {
    is IOException -> "Não foi possível conectar à API."
    is HttpException -> when (error.code()) {
        401 -> "Sua sessão expirou. Faça login novamente."
        403 -> "Você não tem permissão para executar esta ação."
        409 -> "Esta solicitação já foi processada ou possui dados duplicados."
        else -> "Erro na API (HTTP ${error.code()})."
    }
    else -> error.message ?: "Não foi possível concluir a operação."
}
