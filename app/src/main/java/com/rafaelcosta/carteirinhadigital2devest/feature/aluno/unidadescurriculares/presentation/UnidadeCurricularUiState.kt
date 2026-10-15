package com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.presentation

import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

data class UnidadeCurricularUiState(
    val isLoading: Boolean = false,
    val unidades: List<UnidadeCurricular> = emptyList(),
    val errorMessage: String? = null
)