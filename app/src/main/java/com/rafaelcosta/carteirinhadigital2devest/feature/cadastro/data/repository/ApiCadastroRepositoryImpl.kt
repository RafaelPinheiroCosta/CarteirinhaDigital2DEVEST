package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.data.repository

import com.rafaelcosta.carteirinhadigital2devest.core.network.apiErrorMessage
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.data.remote.dto.CadastroRequestDto
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.data.remote.service.CadastroPublicoApi
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.model.SolicitacaoCadastro
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.repository.CadastroRepository
import retrofit2.HttpException

class ApiCadastroRepositoryImpl(private val api: CadastroPublicoApi) : CadastroRepository {
    override suspend fun listarCursos(): Result<List<String>> =
        runCatching { api.listarCursos() }.recoverCatching {
            throw IllegalStateException(apiErrorMessage(it))
        }

    override suspend fun solicitarCadastro(solicitacao: SolicitacaoCadastro): Result<Unit> =
        runCatching {
            val response = api.solicitarCadastro(
                CadastroRequestDto(
                    nome = solicitacao.nome,
                    login = solicitacao.login,
                    senha = solicitacao.senha,
                    perfil = solicitacao.perfil.name,
                    curso = solicitacao.curso.orEmpty()
                )
            )
            if (!response.isSuccessful) throw HttpException(response)
            Unit
        }.recoverCatching { throw IllegalStateException(apiErrorMessage(it)) }
}
