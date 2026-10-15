package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.PerfilAcesso
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.repository.LoginRepository
import kotlinx.coroutines.delay

class FakeAuthRepository : LoginRepository {

    override suspend fun login(
        login: String,
        senha: String
    ): Result<UsuarioLogado> {

        delay(1500)

        if (senha != "123") {

            return Result.failure(
                IllegalArgumentException(
                    "Login ou senha inválidos"
                )
            )
        }

        return when {

            login.equals(
                "aluno",
                ignoreCase = true
            ) -> {

                Result.success(
                    UsuarioLogado(
                        id = "1",
                        nome = "Rafael Costa",
                        matricula = "2026000001",
                        perfil = PerfilAcesso.ALUNO,
                        curso = "Desenvolvimento de Sistemas",
                        turma = "2DEVEST-A",
                        token = "token-fake-aluno"
                    )
                )
            }

            login.equals(
                "professor",
                ignoreCase = true
            ) -> {

                Result.success(
                    UsuarioLogado(
                        id = "2",
                        nome = "Professor Modelo",
                        matricula = "PROF20260001",
                        perfil = PerfilAcesso.PROFESSOR,
                        curso = "",
                        turma = "",
                        token = "token-fake-professor"
                    )
                )
            }

            else -> {

                Result.failure(
                    IllegalArgumentException(
                        "Login ou senha inválidos"
                    )
                )
            }
        }
    }
}