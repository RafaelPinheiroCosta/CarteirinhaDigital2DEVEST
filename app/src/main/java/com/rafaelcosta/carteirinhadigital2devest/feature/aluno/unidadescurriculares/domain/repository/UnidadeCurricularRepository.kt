package com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.domain.repository

import com.rafaelcosta.carteirinhadigital2devest.feature.aluno.unidadescurriculares.domain.model.UnidadeCurricular

interface UnidadeCurricularRepository {
    suspend fun listar():Result<List<UnidadeCurricular>>
}