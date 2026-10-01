package com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data.remote.service

import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data.remote.dto.UnidadeCurricularDto
import retrofit2.http.GET

interface UnidadeCurricularApi {

    @GET("unidades-curriculares")
    suspend fun listar(): List<UnidadeCurricularDto>
}