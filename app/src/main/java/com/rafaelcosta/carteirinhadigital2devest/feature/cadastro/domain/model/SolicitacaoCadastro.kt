package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.domain.model

import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado

data class SolicitacaoCadastro(
    val nome: String,
    val login: String,
    val senha: String,
    val perfil: PerfilSolicitado,
    val curso: String?
)
