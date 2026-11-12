package com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.repository

import com.rafaelcosta.carteirinhadigital2devest.core.network.apiErrorMessage
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.dto.AprovarCadastroDto
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.service.GestaoSolicitacoesApi
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.*
import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.repository.GestaoSolicitacoesRepository
import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.model.PerfilSolicitado
import retrofit2.HttpException

class ApiGestaoSolicitacoesRepositoryImpl(private val api: GestaoSolicitacoesApi) :
    GestaoSolicitacoesRepository {
    override suspend fun listarPendentes(perfil: PerfilSolicitado): Result<List<SolicitacaoPendente>> =
        runCatching { api.listarPendentes(perfil.name).map { it.toDomain() } }
            .recoverCatching { throw IllegalStateException(apiErrorMessage(it)) }

    override suspend fun listarTurmas(): Result<List<TurmaCatalogo>> =
        runCatching { api.listarTurmas().map { it.toDomain() } }
            .recoverCatching { throw IllegalStateException(apiErrorMessage(it)) }

    override suspend fun aprovar(id: String, aprovacao: AprovacaoSolicitacao): Result<Unit> =
        runCatching {
            val response = api.aprovar(id, AprovarCadastroDto.fromDomain(aprovacao))
            if (!response.isSuccessful) throw HttpException(response)
            Unit
        }.recoverCatching { throw IllegalStateException(apiErrorMessage(it)) }

    override suspend fun rejeitar(id: String): Result<Unit> =
        runCatching {
            val response = api.rejeitar(id)
            if (!response.isSuccessful) throw HttpException(response)
            Unit
        }.recoverCatching { throw IllegalStateException(apiErrorMessage(it)) }
}
