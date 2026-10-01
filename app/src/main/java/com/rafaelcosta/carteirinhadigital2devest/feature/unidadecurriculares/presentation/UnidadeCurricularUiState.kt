package com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.presentation

import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val listaUnidadesCurriculares: List<UnidadeCurricular> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
) {
}