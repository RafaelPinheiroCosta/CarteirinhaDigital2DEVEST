package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.model.SolicitacaoCadastro

interface CadastroRepository {
    suspend fun listarCursos(): Result<List<String>>
    suspend fun solicitarCadastro(solicitacao: SolicitacaoCadastro): Result<Unit>
}
