package com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.dto

import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.SolicitacaoPendente
import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado
import kotlinx.serialization.Serializable

@Serializable
data class SolicitacaoPendenteDto(
    val id: String,
    val nome: String,
    val login: String,
    val perfilSolicitado: String,
    val cursoSolicitado: String
) {
    fun toDomain(): SolicitacaoPendente = SolicitacaoPendente(
        id, nome, login, PerfilSolicitado.valueOf(perfilSolicitado), cursoSolicitado
    )
}
