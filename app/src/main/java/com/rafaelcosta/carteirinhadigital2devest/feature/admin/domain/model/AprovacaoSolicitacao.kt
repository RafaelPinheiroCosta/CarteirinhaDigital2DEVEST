package com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model

sealed interface AprovacaoSolicitacao {
    data class Aluno(val turma: String, val matricula: String) : AprovacaoSolicitacao
    data class Professor(val atribuicoes: List<AtribuicaoDocente>) : AprovacaoSolicitacao
}
