package com.rafaelcosta.carteirinhadigital2devest.feature.admin.data.remote.dto

import com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model.TurmaCatalogo
import kotlinx.serialization.Serializable

@Serializable
data class TurmaCatalogoDto(
    val codigo: String,
    val curso: String,
    val unidadesCurriculares: List<String>
) {
    fun toDomain() = TurmaCatalogo(codigo, curso, unidadesCurriculares)
}
