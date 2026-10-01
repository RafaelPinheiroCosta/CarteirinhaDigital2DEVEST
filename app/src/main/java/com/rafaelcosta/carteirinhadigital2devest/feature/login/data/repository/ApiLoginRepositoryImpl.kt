package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.dto.ErrorResponseDto
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.dto.LoginRequestDto
import com.rafaelcosta.carteirinhadigital2devest.feature.login.data.remote.service.AuthApi
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import kotlinx.serialization.json.Json
import retrofit2.HttpException
import java.io.IOException

class ApiLoginRepositoryImpl(
    private val api: AuthApi
) : LoginRepository {

    override suspend fun login(usuario: String, senha: String): Result<UsuarioLogado> {
        return runCatching {
            val response = api.login(LoginRequestDto(login = usuario, senha = senha))
            UsuarioLogado(
                id = response.id,
                nome = response.nome,
                curso = response.curso,
                turma = response.turma,
                token = response.token
            )
        }.recoverCatching { throwable ->
            throw mapToDomainError(throwable)
        }
    }

    private fun mapToDomainError(throwable: Throwable): Throwable {
        return when (throwable) {
            is HttpException -> mapHttpException(throwable)
            is IOException -> IllegalStateException(
                "Não foi possível conectar à API local. Verifique se ela está rodando."
            )
            else -> IllegalStateException(throwable.message ?: "Erro ao fazer login.")
        }
    }

    private fun mapHttpException(exception: HttpException): Throwable {
        if (exception.code() == 401) {
            return IllegalArgumentException("Login ou senha inválidos")
        }

        val messageFromBody = exception.response()?.errorBody()?.string()?.let { body ->
            runCatching {
                Json { ignoreUnknownKeys = true }.decodeFromString<ErrorResponseDto>(body).message
            }.getOrNull()
        }

        return IllegalStateException(messageFromBody ?: "Erro no servidor (${exception.code()}).")
    }
}