package com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}