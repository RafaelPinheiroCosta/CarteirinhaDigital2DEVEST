package com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.*
import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado

interface GestaoSolicitacoesRepository {
    suspend fun listarPendentes(perfil: PerfilSolicitado): Result<List<SolicitacaoPendente>>
    suspend fun listarTurmas(): Result<List<TurmaCatalogo>>
    suspend fun aprovar(id: String, aprovacao: AprovacaoSolicitacao): Result<Unit>
    suspend fun rejeitar(id: String): Result<Unit>
}
