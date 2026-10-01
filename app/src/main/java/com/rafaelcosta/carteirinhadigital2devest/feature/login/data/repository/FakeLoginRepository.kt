package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository

import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.delay

class FakeAuthRepository : LoginRepository {
    override suspend fun login(
        login: String,
        senha: String
    ): Result<UsuarioLogado> {

        delay(1500)

        return if (login.equals("aluno", ignoreCase = true) && senha == "123") {
            Result.success(
                UsuarioLogado(
                    id = "1",
                    nome = "Rafael Costa",
                    matricula = "2026000001",
                    curso = "Desenvolvimento de Sistemas",
                    turma = "2DEVEST-A",
                    token = "token-fake-para-aula"
                )
            )
        } else {
            Result.failure(IllegalArgumentException("Login ou senha inv├ílidos"))
        }
    }
}
