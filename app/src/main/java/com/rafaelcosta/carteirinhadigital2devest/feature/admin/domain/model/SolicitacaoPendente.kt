package com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model

import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado

data class SolicitacaoPendente(
    val id: String,
    val nome: String,
    val login: String,
    val perfilSolicitado: PerfilSolicitado,
    val cursoSolicitado: String
)
