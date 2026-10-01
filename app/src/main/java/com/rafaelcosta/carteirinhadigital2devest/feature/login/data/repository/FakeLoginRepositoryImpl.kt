package com.rafaelcosta.carteirinhadigital2devest.feature.login.data.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.login.domain.model.UsuarioLogado
import kotlinx.coroutines.delay

class FakeLoginRepositoryImpl : LoginRepository {

    override suspend fun login(usuario: String, senha: String)
    : Result<UsuarioLogado> {
        delay(1500)
        return if(usuario.equals("aluno") && senha.equals("123")){
            Result.success(
                UsuarioLogado(
                    id="1",
                    nome = "Rafael Costa",
                    curso = "Desenvolvimento de Sistema",
                    turma = "2DEVEST-B",
                    token = "token-fake-para-aula"
                )
            )
        }else{
            Result.failure(
                IllegalArgumentException(
                        "Login ou senha inválidos"
                )
            )
        }
    }
}