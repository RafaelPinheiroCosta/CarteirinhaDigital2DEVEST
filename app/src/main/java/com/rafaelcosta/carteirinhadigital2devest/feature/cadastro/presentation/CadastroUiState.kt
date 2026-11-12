package com.rafaelcosta.carteirinhadigital2devest.feature.cadastro.presentation

import com.rafaelcosta.carteirinhadigital2devest.core.model.PerfilSolicitado

data class CadastroUiState(
    val nome: String = "",
    val login: String = "",
    val senha: String = "",
    val perfil: PerfilSolicitado = PerfilSolicitado.ALUNO,
    val curso: String = "",
    val cursos: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val enviado: Boolean = false,
    val errorMessage: String? = null
)
